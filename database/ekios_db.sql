-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 01 Jun 2024 pada 14.47
-- Versi server: 10.4.28-MariaDB
-- Versi PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ekios_db`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `tbproduk`
--

CREATE TABLE `tbproduk` (
  `id_produk` varchar(100) NOT NULL,
  `kategori` varchar(255) NOT NULL,
  `nama` varchar(255) NOT NULL,
  `jumlah` int(100) NOT NULL,
  `harga` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `tbproduk`
--

INSERT INTO `tbproduk` (`id_produk`, `kategori`, `nama`, `jumlah`, `harga`) VALUES
('PRD8826', 'mobile legend', 'diamond', 5000, 200000),
('PRD900B', 'pubg', 'uc', 1000, 120000),
('PRD9C52', 'pubg', 'UC', 20, 12000),
('PRD9DC2', 'telkomsel', 'paket data', 14, 50000),
('PRDAB5F', 'mobile legend', 'diamond', 1000, 25000),
('PRDBF3B', 'telkomsel', 'pulsa', 50000, 53000);

-- --------------------------------------------------------

--
-- Struktur dari tabel `tbuser`
--

CREATE TABLE `tbuser` (
  `id_user` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `saldo` int(50) NOT NULL,
  `role` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `tbuser`
--

INSERT INTO `tbuser` (`id_user`, `username`, `password`, `saldo`, `role`) VALUES
(5, 'admin', 'd033e22ae348aeb5660fc2140aec35850c4da997', 0, 'admin'),
(18, 'fadlan', '40bd001563085fc35165329ea1ff5c5ecbdbbeef', 175000, 'customer'),
(19, 'rifai', '5f6955d227a320c7f1f6c7da2a6d96a851a8118f', 0, 'customer'),
(20, 'ijal', '7a698699a9229b278afa72593214582d739b9bad', 50000, 'customer'),
(37, '123', '40bd001563085fc35165329ea1ff5c5ecbdbbeef', 0, 'customer');

-- --------------------------------------------------------

--
-- Struktur dari tabel `transaksi`
--

CREATE TABLE `transaksi` (
  `id_transaksi` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `id_produk` varchar(255) NOT NULL,
  `tanggal` date NOT NULL,
  `total_harga` int(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `transaksi`
--

INSERT INTO `transaksi` (`id_transaksi`, `id_user`, `id_produk`, `tanggal`, `total_harga`) VALUES
(1, 18, 'PRD8826', '2024-05-31', 120000),
(2, 18, 'PRDAB5F', '2024-05-31', 295000),
(3, 18, 'PRD9DC2', '2024-05-31', 270000),
(4, 18, 'PRD9DC2', '2024-05-31', 220000),
(5, 18, 'PRD8826', '2024-05-31', 120000),
(6, 18, 'PRDAB5F', '2024-05-31', 145000),
(7, 18, 'PRD9DC2', '2024-05-31', 95000),
(8, 18, 'PRD900B', '2024-06-01', 75000);

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `tbproduk`
--
ALTER TABLE `tbproduk`
  ADD PRIMARY KEY (`id_produk`);

--
-- Indeks untuk tabel `tbuser`
--
ALTER TABLE `tbuser`
  ADD PRIMARY KEY (`id_user`);

--
-- Indeks untuk tabel `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`id_transaksi`),
  ADD KEY `id_user` (`id_user`,`id_produk`),
  ADD KEY `id_produk` (`id_produk`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `tbuser`
--
ALTER TABLE `tbuser`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=38;

--
-- AUTO_INCREMENT untuk tabel `transaksi`
--
ALTER TABLE `transaksi`
  MODIFY `id_transaksi` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `transaksi`
--
ALTER TABLE `transaksi`
  ADD CONSTRAINT `transaksi_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `tbuser` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `transaksi_ibfk_2` FOREIGN KEY (`id_produk`) REFERENCES `tbproduk` (`id_produk`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
