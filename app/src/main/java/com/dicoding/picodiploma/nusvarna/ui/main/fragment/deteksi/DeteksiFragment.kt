package com.dicoding.picodiploma.nusvarna.ui.main.fragment.deteksi

import android.app.Application
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import com.dicoding.picodiploma.nusvarna.api.ApiConfig
import com.dicoding.picodiploma.nusvarna.databinding.FragmentDeteksiBinding
import com.dicoding.picodiploma.nusvarna.response.DetailResponse
import com.dicoding.picodiploma.nusvarna.ui.auth.RegisterActivity
import com.dicoding.picodiploma.nusvarna.ui.detaildeteksi.DetailDeteksiActivity
import com.dicoding.picodiploma.nusvarna.ui.main.MainActivity
import com.dicoding.picodiploma.nusvarna.utils.createFile
import com.dicoding.picodiploma.nusvarna.utils.reduceFileImage
import com.dicoding.picodiploma.nusvarna.utils.uriToFile
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File


class DeteksiFragment : Fragment() {

    private lateinit var binding: FragmentDeteksiBinding
    private var imageCapture: ImageCapture? = null
    private var cameraSelector: CameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

    private var getFile: File? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDeteksiBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //tempat buat kamera
        binding.captureImage.setOnClickListener {
            takePhoto()
        }
        binding.switchCamera.setOnClickListener {
            cameraSelector = if (cameraSelector == CameraSelector.DEFAULT_BACK_CAMERA) CameraSelector.DEFAULT_FRONT_CAMERA
            else CameraSelector.DEFAULT_BACK_CAMERA
            startCamera()
        }
        binding.switchCaera.setOnClickListener { startGallery() }

    }

    override fun onResume() {
        super.onResume()
        startCamera()
    }

    private fun takePhoto() {
        val imageCapture = imageCapture ?: return
        val application = requireActivity().application

        val photoFile = createFile(application)

        val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()
        imageCapture.takePicture(
            outputOptions,
            ContextCompat.getMainExecutor(requireContext()),
            object : ImageCapture.OnImageSavedCallback {
                override fun onError(exc: ImageCaptureException) {
                    Toast.makeText(
                        requireContext(),
                        "Gagal mengambil gambar.",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onImageSaved(output: ImageCapture.OutputFileResults) {
                    val requestImageFile = photoFile.asRequestBody("image/*".toMediaTypeOrNull())
                    val imageMultiPart: MultipartBody.Part = MultipartBody.Part.createFormData(
                        "image",
                        photoFile.name,
                        requestImageFile
                    )
                    val apiService = ApiConfig.getApiService()
                    val uploadImageRequest = apiService.getPredict(imageMultiPart)
                    uploadImageRequest.enqueue(object : Callback<DetailResponse> {
                        override fun onResponse(
                            call: Call<DetailResponse>,
                            response: Response<DetailResponse>
                        ) {
                            if (response.isSuccessful) {
                                val responseBody = response.body()
                                if (responseBody != null) {
                                    Toast.makeText(requireContext(), responseBody.predictedLabel, Toast.LENGTH_SHORT).show()
                                    val intentDetail = Intent(requireContext(), DetailDeteksiActivity::class.java)
                                    intentDetail.putExtra("responseBody", responseBody)
                                    startActivity(intentDetail)
                                }
                            } else {
                                Toast.makeText(requireContext(), response.message(), Toast.LENGTH_SHORT).show()
                            }
                        }
                        override fun onFailure(call: Call<DetailResponse>, t: Throwable) {
                            Toast.makeText(requireContext(), t.message, Toast.LENGTH_SHORT).show()
                        }

                    })

                }
            }
        )
    }

    private fun startCamera() {

        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())

        cameraProviderFuture.addListener({
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()
            val preview = Preview.Builder()
                .build()
                .also {
                    it.setSurfaceProvider(binding.viewFinder.surfaceProvider)
                }

            imageCapture = ImageCapture.Builder().build()

            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(
                    this,
                    cameraSelector,
                    preview,
                    imageCapture
                )

            } catch (exc: Exception) {
                Toast.makeText(
                    requireContext(),
                    "Gagal memunculkan kamera.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }, ContextCompat.getMainExecutor(requireContext()))
    }

    private val launcherIntentGallery = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == AppCompatActivity.RESULT_OK) {
            val selectedImg = result.data?.data as Uri
            selectedImg.let { uri ->
                val myFile = uriToFile(uri, requireContext())
                getFile = myFile
                uploadImage()
            }
        }
    }

    private fun startGallery() {
        val intent = Intent()
        intent.action = Intent.ACTION_GET_CONTENT
        intent.type = "image/*"
        val chooser = Intent.createChooser(intent, "Choose a Picture")
        launcherIntentGallery.launch(chooser)
    }

    private fun uploadImage() {
        if (getFile != null) {
            val file = reduceFileImage(getFile as File)

            val requestImageFile = file.asRequestBody("image/*".toMediaTypeOrNull())
            val imageMultiPart: MultipartBody.Part = MultipartBody.Part.createFormData(
                "image",
                file.name,
                requestImageFile
            )
            val apiService = ApiConfig.getApiService()
            val uploadImageRequest = apiService.getPredict(imageMultiPart)
            uploadImageRequest.enqueue(object : Callback<DetailResponse> {
                override fun onResponse(
                    call: Call<DetailResponse>,
                    response: Response<DetailResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            Toast.makeText(requireContext(), responseBody.predictedLabel, Toast.LENGTH_SHORT).show()
                            val intentDetail = Intent(requireContext(), DetailDeteksiActivity::class.java)
                            intentDetail.putExtra("responseBody", responseBody.predictedLabel)
                            startActivity(intentDetail)
                        }
                    } else {
                        Toast.makeText(requireContext(), response.message(), Toast.LENGTH_SHORT).show()
                    }
                }
                override fun onFailure(call: Call<DetailResponse>, t: Throwable) {
                    Toast.makeText(requireContext(), t.message, Toast.LENGTH_SHORT).show()
                }

            })


        } else {
            Toast.makeText(requireContext(), "Silakan masukkan berkas gambar terlebih dahulu.", Toast.LENGTH_SHORT).show()
        }
    }


    companion object {
        fun newInstance() : DeteksiFragment {
            return DeteksiFragment(  )
        }    }
}
