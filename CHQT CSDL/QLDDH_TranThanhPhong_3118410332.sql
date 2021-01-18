-- Tạo CSDL QLDDH Start --

-- Ban đầu ta đứng DATABASE master để tạo DATABASE QLDDH
USE master
GO

-- Nếu tồn tại CSDL QLDDH -> DROP DATABASE QLDDH -> CREATE DATABASE QLDDH
IF EXISTS (
  SELECT name -- Tìm kiếm tên
   FROM sys.databases -- Trong system databases
   WHERE name = N'QLDDH' -- Name = N"QLDDH"
) -- Nếu tồn tại
-- Không có GO

-- Nếu tồn tại QLDDH thì ta xóa QLDDH xong mới đi tiếp
DROP DATABASE QLDDH
GO

-- Tạo mới CSDL QLDDH
CREATE DATABASE QLDDH
GO

-- Tạo CSDL QLDDH End --

-- Sử dụng DATABASE QLDDH và bắt đầu tạo TABLE Start --

USE QLDDH
GO

-- Tạo bảng Hàng Hóa
CREATE TABLE HangHoa(
    MaHH    CHAR(5),
    TenHH   NVARCHAR(50),
    DVT     NVARCHAR(20),
    SLCon   SMALLINT,
    DonGiaHH    int,
    CONSTRAINT pk_HH PRIMARY KEY(MaHH)
)
GO

-- Tạo bảng Khách Hàng
CREATE TABLE KhachHang(
    MaKH CHAR(5),
    TenKH NVARCHAR(50),
    DiaChi NVARCHAR(50),
    DienThoai CHAR(10),
    CONSTRAINT pk_KH PRIMARY KEY(MaKH)
)
GO

-- Tạo bảng Phiếu Giao Hàng
CREATE TABLE PhieuGiaoHang(
    MaGiao CHAR(5),
    NgayGiao SMALLDATETIME,
    NgayDat SMALLDATETIME,
    CONSTRAINT pk_PGH PRIMARY key(MaGiao)
)
GO

-- Tạo bảng Chi Tiết Giao Hàng
CREATE TABLE ChiTietGiaoHang(
    MaGiao CHAR(5),
    MaHH CHARACTER(5),
    SLGiao SMALLINT,
    DonGiaGiao INT,
    CONSTRAINT pk_CTGH PRIMARY KEY(MaHH,MaGiao)
)
GO

-- Tạo bảng Đơn Hàng
CREATE TABLE DonDatHang(
    MaDat CHAR(5),
    NgayDat SMALLDATETIME,
    MaKH CHAR(5),
    TinhTrang BIT,
    CONSTRAINT pk_DDH PRIMARY KEY(MaDat)
)
GO

-- Tạo bảng Chi Tiết Đặt Hàng
CREATE TABLE ChiTietDatHang(
    MaDat CHAR(5),
    MaHH CHAR(5),
    SLDat SMALLINT,
    CONSTRAINT pk_CTDH PRIMARY KEY(MaDat,MaHH)
)
GO

-- Tạo bảng Lịch Sử Giá
CREATE TABLE LichSuGia(
    MaHH CHAR(5),
    NgayHL SMALLDATETIME,
    DonGia int,
    CONSTRAINT pk_LSG PRIMARY KEY(MaHH,NgayHL)
)
GO

-- Sử dụng DATABASE QLDDH và bắt đầu tạo TABLE End --

-- Tạo khóa ngoại và mối quan hệ giữa các TABLE Start --

-- Tạo khóa ngoại giữa các bản ChiTietDatHang với DonDatHang và HangHoa
ALTER TABLE ChiTietDatHang -- Tên bảng tham chiếu
ADD CONSTRAINT fk_CTHD_MaDat FOREIGN KEY(MaDat) REFERENCES DonDatHang(MaDat)
ON UPDATE CASCADE ON DELETE CASCADE, -- MaDat của CTDH phụ thuộc vào MaDat của DonDatHang
CONSTRAINT fk_CTHD_MaHH FOREIGN KEY(MaHH) REFERENCES HangHoa(MaHH)
ON UPDATE CASCADE ON DELETE CASCADE -- MaHH của CTDH phụ thuộc vào MaHH của HangHoa
-- UPDATE AND DELETE phụ thuộc vào khóa của nó ở bảng DonDatHang và HangHoa

-- Tạo khóa ngoại giữa các bản ChiTietGiaoHang với PhieuGiaoHang và HangHoa
ALTER TABLE ChiTietGiaoHang
ADD CONSTRAINT fk_CTGH_MaGiao FOREIGN KEY(MaGiao) REFERENCES PhieuGiaoHang(MaGiao)
ON UPDATE CASCADE ON DELETE CASCADE,
CONSTRAINT fk_CTGH_MaHH FOREIGN KEY(MaHH) REFERENCES HangHoa(MaHH)
ON UPDATE CASCADE ON DELETE CASCADE

-- Tạo khóa ngoại giữa bảng LichSuGia và HangHoa
ALTER TABLE LichSuGia
ADD CONSTRAINT fk_LSG_MaHH FOREIGN KEY(MaHH) REFERENCES HangHoa(MaHH)
ON UPDATE CASCADE ON DELETE CASCADE

-- Tạo khóa ngoại giữa bảng DonDatHang và KhachHang
ALTER TABLE DonDatHang
ADD CONSTRAINT fk_DDH_MaKH FOREIGN KEY(MAKH) REFERENCES KhachHang(MaKH)
ON UPDATE CASCADE ON DELETE CASCADE

-- Tạo khóa ngoại giữa bảng PhieuGiaoHang và DonDatHang
ALTER TABLE PhieuGiaoHang
ADD CONSTRAINT fk_PGH_MaGiao FOREIGN KEY(MaGiao) REFERENCES DonDatHang(MaDat)
ON UPDATE CASCADE ON DELETE CASCADE

-- Tạo khóa ngoại và mối quan hệ giữa các TABLE End --

-- Trần Thanh Phong 3118410322 DCT1188