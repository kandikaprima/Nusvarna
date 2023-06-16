package com.dicoding.picodiploma.nusvarna.data

import android.provider.ContactsContract.Data

object DataDetailDeteksiData {


    //berikut adalah contoh dari menambahkan data recyclerview
    val dataDetail = listOf(
        DataDetailDeteksi(
            "bali",
            //baju
            arrayOf("https://i.ibb.co/rHBvqm2/Payas-Agung1.jpg","https://i.ibb.co/vsSxRYg/Payas-Agung.jpg","https://i.ibb.co/mBzVb9b/Payas-Agung.png"),
            "Baju Payas Madya Bali",
            "Bali",
            //tempat wisata
            arrayOf("https://i.ibb.co/3dzq88P/Uluwatu.jpg", "https://i.ibb.co/9vDLPSx/Tegalalang-Rice-Terrace.jpg", "https://i.ibb.co/TLQgZwG/Pura-Besakih.jpg", "https://i.ibb.co/3yYmQnY/Tanah-Lot.jpg", "https://i.ibb.co/VtRwsLw/Ubud-Bali.jpg"),
            arrayOf("Uluwatu", "Tegalalang", "Pura Besakih", "Tanah Lot", "Ubud"),
            //makanan khas
            arrayOf("https://i.ibb.co/b2kcCg8/Jaje-Bali.jpg", "https://i.ibb.co/sQDqW5B/Sambal-Matah.jpg", "https://i.ibb.co/hYySCCz/Babi-Guling.jpg", "https://i.ibb.co/kxSJNPM/Ayam-Betutu.jpg"),
            arrayOf("Jaje", "Sambal Matah", "Babi Guling", "Ayam Betutu"),
            //bangunan bersejarah
            arrayOf("https://i.ibb.co/Wfr39DS/Goa-Gajah.jpg", "https://i.ibb.co/z47ZLZC/Pura-Ulun-Danu-Bratan.jpg", "https://i.ibb.co/3yYmQnY/Tanah-Lot.jpg", "https://i.ibb.co/TLQgZwG/Pura-Besakih.jpg"),
            arrayOf("Goa Gajah", "Pura Ulun", "Tanah Lot", "Pura Besakih")
        ),
        DataDetailDeteksi(
            "dayak",
            //baju
            arrayOf("https://i.ibb.co/b7LmKGm/Kalimantan-Timur.jpg","https://i.ibb.co/601x6Bc/Kalimantan-Barat.jpg","https://i.ibb.co/Rc3G5HW/baju-adat-dayak.jpg"),
            "Dayak",
            "Kalimantan Timur",
            //tempat wisata
            arrayOf("https://dynamic-media-cdn.tripadvisor.com/media/photo-o/09/89/3d/ba/sungai-mahakam.jpg?w=500&h=-1&s=1", "https://dynamic-media-cdn.tripadvisor.com/media/photo-o/0c/f0/4f/a9/this-is-typical-golden.jpg?w=500&h=-1&s=1", "https://dynamic-media-cdn.tripadvisor.com/media/photo-o/05/f4/e1/a9/derawan-archipelago.jpg?w=500&h=-1&s=1", "https://dynamic-media-cdn.tripadvisor.com/media/photo-o/10/c0/18/f7/photo2jpg.jpg?w=500&h=400&s=1", "https://dynamic-media-cdn.tripadvisor.com/media/photo-o/08/1f/db/e3/bontang-kuala-village.jpg?w=500&h=-1&s=1"),
            arrayOf("Sungai Mahakam", "Danau Ubur Ubur", "Derawan", "Maratua", "Bontang Kuala"),
            //makanan khas
            arrayOf("https://i.ibb.co/S7LNjFC/Lemang.jpg", "https://i.ibb.co/s94FRP1/Ikan-pain-bakar.jpg", "https://i.ibb.co/xDqG8FX/Ampyang.png", "https://ibb.co/4P2cLqC"),
            arrayOf("Lemang", "Patin Bakar", "Ampyang", "Soto Banjar"),
            //bangunan bersejarah
            arrayOf("https://i.ibb.co/fG4vw6v/Taman-Budaya-Kalimantan-Tengah.png", "https://i.ibb.co/SQLGvqh/Museum-Mulawarman.jpg", "https://i.ibb.co/yq9MBkk/Rumah-Betang-1.jpg", "https://dynamic-media-cdn.tripadvisor.com/media/photo-o/08/67/34/98/keraton-sambaliung.jpg?w=500&h=400&s=1"),
            arrayOf("Taman Budaya", "Museum Mulawarman", "Rumah Betang", "Keraton Sambaliung")
        ),
        DataDetailDeteksi(
            "asmat",
            //baju
            arrayOf("https://i.ibb.co/pf8ZyN7/papua.jpg","https://i.ibb.co/kyGJ5hV/Pakaian-Adat-Papua.jpg","https://i.ibb.co/D9GbY8Y/papua1.jpg"),
            "Asmat",
            "Papua",
            //tempat wisata
            arrayOf("https://i.ibb.co/rd918Cm/Raja-Ampat.jpg", "https://i.ibb.co/kqC6ft0/Pulau-Biak-1.jpg", "https://i.ibb.co/c6wvt2m/Kota-Jayapura.jpg", "https://i.ibb.co/L0sHGJT/Baliem.jpg", "https://i.ibb.co/4Jg27rH/Wamena.jpg"),
            arrayOf("Raja Ampat", "Pulau Biak", "Jayapura", "Baliem", "Wamena"),
            //makanan khas
            arrayOf("https://i.ibb.co/LzxxHWf/Kuah-Asam-ikan.jpg", "https://i.ibb.co/vzV4S3S/Matoa.jpg", "https://i.ibb.co/RpbsyJp/Sago-Worms.jpg", "https://i.ibb.co/3SVyy0K/colocolo.jpg"),
            arrayOf("Kuah Asam Ikan", "Matoa", "Ulat Sagu", "Colo Colo"),
            //bangunan bersejarah
            arrayOf("https://i.ibb.co/CQ3cFky/Rumah-Honai.jpg", "https://i.ibb.co/9rjs5XC/Benteng-Du-Bus.jpg", "https://i.ibb.co/0Ydgt0c/tugu-mcarthur.jpg", "https://i.ibb.co/F7FDTHQ/Museum-Loka-Budaya-Papua.jpg"),
            arrayOf("Rumah Honai", "Benteng Du Bus", "Tugu McArthur", "Museum Loka Budaya")
        ),
        DataDetailDeteksi(
            "minang",
            //baju
            arrayOf("https://i.ibb.co/6s6SWW5/600d8ab6-6e15-4505-bae6-bfb0457f2b40.jpg", "https://i.ibb.co/wYs65H9/9d6d19ce-74c3-4b7c-be34-35b746af2373.jpg", "https://i.ibb.co/G3gw0DR/Baju-Pengantin-Minang.webp"),
            "Minang",
            "Sumatera Barat",
            //tempat wisata
            arrayOf("https://i.ibb.co/C7ycFrZ/pantai-carolina.jpg", "https://i.ibb.co/J5GygPh/lubuk-nyarai.jpg", "https://i.ibb.co/NLTVMLp/Danau-Maninjau.jpg", "https://i.ibb.co/pKQqhVf/Jam-Gadang.jpg", "https://i.ibb.co/jR9hwL8/ngarai-sianol.jpg"),
            arrayOf("Pantai Carolina", "Lubuk Nyarai", "Danau Maninjau", "Jam Gadang", "Ngarai Sianok"),
            //makanan khas
            arrayOf("https://i.ibb.co/vV6ZyR5/Dendeng-Batakok.png", "https://i.ibb.co/wMtSNp5/Rendang.jpg", "https://i.ibb.co/27rW0JN/Gulai.jpg", "https://i.ibb.co/6Y2pndg/Sate-Padang.jpg"),
            arrayOf("Dendeng", "Rendang", "Gulai", "Sate Padang"),
            //bangunan bersejarah
            arrayOf("https://i.ibb.co/NKv8tkM/Masjid-Raya-Sumatera-Barat.jpg", "https://i.ibb.co/Rbk2Knz/Istana-Pagaruyung.jpg", "https://i.ibb.co/6Z06zqc/jembatan-siti-nurbaya.jpg", "https://i.ibb.co/DgGQKkX/Rumah-Gadang.jpg"),
            arrayOf("Masjid Raya Sumbar", "Istana Pagaruyung", "Jembatan Siti N.", "Rumah Gadang")
        ),
        DataDetailDeteksi(
            "madura",
            //baju
            arrayOf("https://i.ibb.co/SsGz6mr/madura1.jpg", "https://i.ibb.co/VxvVZhN/madura2.webp", "https://i.ibb.co/LhTYTfb/madura3.jpg"),
            "Madura",
            "Jawa Timur",
            //tempat wisata
            arrayOf("https://i.ibb.co/drRLb6t/Kawah-Ijen.jpg", "https://i.ibb.co/sgkKFt4/bukit-kapur.jpg", "https://i.ibb.co/Jsv9C8k/Taman-Nasional-Bromo-Tengger-Semeru.jpg", "https://i.ibb.co/Mg5Mn7r/gili-genting.jpg", "https://i.ibb.co/hcdf033/bukit-tinggi.jpg"),
            arrayOf("Kawah Ijen", "Bukit Kapur", "Gunung Bromo", "Gili Genting", "Bukit Tinggi"),
            //makanan khas
            arrayOf("https://i.ibb.co/XCHNp2n/Rawon.jpg", "https://i.ibb.co/G2XdQYV/Nasi-Liwet1.jpg", "https://i.ibb.co/qWrnWjX/Pecel.jpg", "https://i.ibb.co/VCRTn8f/Pindang-Bandeng.jpg"),
            arrayOf("Rawon", "Nasi Liwet", "Pecel", "Pindang Bandeng"),
            //bangunan bersejarah
            arrayOf("https://i.ibb.co/fxH8xhc/Candi-Prambanan-1.jpg", "https://i.ibb.co/db4HqNZ/Benteng-Pendem.jpg", "https://i.ibb.co/kSWqtdb/Masjid-Agung-Demak.jpg", "https://i.ibb.co/JmMKxv1/Keraton-Surakarta.jpg"),
            arrayOf("Prambanan", "Benteng Pendem", "Masjid Agung Demak", "Keraton")
        ),
        DataDetailDeteksi(
            "bugis",
            //baju
            arrayOf("https://i.ibb.co/7t6WSSx/4.jpg", "https://i.ibb.co/Qn0QGFy/2.jpg", "https://i.ibb.co/cgGd0Zw/3.jpg"),
            "Bugis",
            "Sulawesi Selatan",
            //tempat wisata
            arrayOf("https://i.ibb.co/LRpPdGP/pantai-losari.jpg", "https://i.ibb.co/7443MTV/tanjung-bira.jpg", "https://i.ibb.co/pJNjH1Z/Taman-Nasional-Bantimurung-Bulusaraung1.jpg", "https://i.ibb.co/jhSF59x/Danau-Tempe.jpg", "https://i.ibb.co/30ZtXs1/Malino-Highland1.jpg"),
            arrayOf("Pantai Losari", "Tanjung Bira", "Bantimurung", "Danau Tempe", "Bukit Malino"),
            //makanan khas
            arrayOf("https://i.ibb.co/TLbNFz5/Kapurung.webp", "https://i.ibb.co/rZwZMVk/Mie-Kering.jpg", "https://i.ibb.co/Jk8xQWD/Sop-Saudara.jpg", "https://i.ibb.co/zV8Y367/Pisang-Epe.jpg"),
            arrayOf("Kapurung", "Mie Kering", "Sop Saudara", "Pisang Epe"),
            //bangunan bersejarah
            arrayOf("https://i.ibb.co/wSGxqzn/Benteng-Somba-Opu-1.jpg", "https://i.ibb.co/7GZtp0g/Rumah-Adat-Tongkonan.jpg", "https://i.ibb.co/6YHHh31/la-galigo.jpg", "https://i.ibb.co/CMYKFpM/Sultan-Hasanudin-Museum.jpg"),
            arrayOf("Benteng Somba Opu", "Rumah Tongkonan", "Museum La Galigo", "Museum Sultan Hasanudin")
        ),
    )



//    dan ini adalah cara untuk memanggilnya
//    dataDetail[0].picture[1]

//    pertama, buat fungsi untuk mencari objek array berdasarkan nama
//fun getObjectByName(name: String): Object? {
//    for (obj in objects) {
//        if (obj.name == name) {
//            return obj
//        }
//    }
//    return null
//}
}