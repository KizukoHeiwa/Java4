GO
CREATE DATABASE ASM_JAVA4;
GO

--DROP DATABASE ASM_JAVA4;

USE ASM_JAVA4;
GO

CREATE TABLE VIDEO (
	ID CHAR(14) NOT NULL,
	TITLE NVARCHAR(255) NULL,
	POSTER NVARCHAR(255) NULL,
	VIEWS INT NULL,
	DESCRIPTION NVARCHAR(MAX) NULL,
	ACTIVE BIT NULL,

	CONSTRAINT PK_VIDEO PRIMARY KEY(ID),
);

CREATE TABLE USERS(
	ID CHAR(14) NOT NULL,
	PASSWORD VARCHAR(50) NOT NULL,
	EMAIL VARCHAR(255) NULL,
	FULLNAME NVARCHAR(50) NULL,
	ADMIN BIT NULL,

	CONSTRAINT PK_USER PRIMARY KEY(ID)
);

CREATE TABLE FAVORITE (
	ID CHAR(14) NOT NULL,
	USERID CHAR(14) NOT NULL,
	VIDEOID CHAR(14) NOT NULL,
	LIKEDATE DATE NULL,

	CONSTRAINT PK_FAVORITE PRIMARY KEY(ID),
	CONSTRAINT FK_FAVORITE_USERS FOREIGN KEY(USERID) REFERENCES USERS(ID),
	CONSTRAINT FK_FAVORITE_VIDEO FOREIGN KEY(VIDEOID) REFERENCES VIDEO(ID)
);

CREATE TABLE SHARE (
	ID CHAR(14) NOT NULL,
	USERID CHAR(14) NOT NULL,
	VIDEOID CHAR(14) NOT NULL,
	EMAILS VARCHAR(255) NULL,
	SHAREDATE DATE NULL,

	CONSTRAINT PK_SHARE PRIMARY KEY(ID),
	CONSTRAINT FK_SHARE_USERS FOREIGN KEY(USERID) REFERENCES USERS(ID),
	CONSTRAINT FK_SHARE_VIDEO FOREIGN KEY(VIDEOID) REFERENCES VIDEO(ID)
);

INSERT INTO USERS (ID, PASSWORD, EMAIL, FULLNAME, ADMIN) VALUES
('20241103070122', 'password123', 'nguyenthaibinh@gmail.com', N'Nguyễn Thái Bình', 0),
('20241103120115', 'password456', 'tranthimai@gmail.com', N'Trần Thị Mai', 1),
('20241104150608', 'password789', 'levanhung@gmail.com', N'Lê Văn Hùng', 0),
('20241105235945', 'password101', 'phamminhanh@gmail.com', N'Phạm Minh Anh', 0),
('20241106112411', 'password112', 'hoangthilan@gmail.com', N'Hoàng Thị Lan', 1),
('20241107181737', 'password212', 'nguyenhuuphuc@gmail.com', N'Nguyễn Hữu Phúc', 0),
('20241108004519', 'password313', 'phamvankhanh@gmail.com', N'Phạm Văn Khánh', 0),
('20241108060122', 'password414', 'tranvanminh@gmail.com', N'Trần Văn Minh', 1),
('20241109122356', 'password515', 'buithihuong@gmail.com', N'Bùi Thị Hương', 0),
('20241110020412', 'password616', 'vutienbat@gmail.com', N'Vũ Tiến Đạt', 0),
('20241111032223', 'password717', 'huynhkimhoa@gmail.com', N'Huỳnh Kim Hoa', 1),
('20241112080845', 'password818', 'phungminhtu@gmail.com', N'Phùng Minh Tú', 0),
('20241113095416', 'password919', 'dinhthutrang@gmail.com', N'Đinh Thị Trang', 0),
('20241114050751', 'password020', 'leminhtam@gmail.com', N'Lê Minh Tâm', 0),
('20241115032544', 'password121', 'phandangthanh@gmail.com', N'Phan Đăng Thành', 1),
('20241116142836', 'password222', 'doanthithao@gmail.com', N'Đoàn Thị Thảo', 0),
('20241117191322', 'password323', 'dangxuanminh@gmail.com', N'Đặng Xuân Minh', 0),
('20241118071910', 'password424', 'lethuongvan@gmail.com', N'Lê Thương Vân', 1),
('20241119124558', 'password525', 'hoangthibao@gmail.com', N'Hoàng Thị Bảo', 0),
('20241120065832', 'password626', 'nguyenhuonglinh@gmail.com', N'Nguyễn Hương Linh', 0);

INSERT INTO VIDEO (ID, TITLE, POSTER, VIEWS, DESCRIPTION, ACTIVE) VALUES
('20241103060110', N'Hướng dẫn học lập trình Python cơ bản', 'https://img.youtube.com/vi/sample1.jpg', 120000, N'Khóa học lập trình Python cho người mới bắt đầu', 1),
('20241104073433', N'10 mẹo tăng hiệu suất làm việc với Excel', 'https://img.youtube.com/vi/sample2.jpg', 85000, N'Trọn bộ các mẹo sử dụng Excel hiệu quả trong công việc', 1),
('20241104120609', N'Chuyến du lịch Sapa cùng gia đình', 'https://img.youtube.com/vi/sample3.jpg', 54000, N'Video ghi lại chuyến đi Sapa tuyệt đẹp cùng gia đình', 1),
('20241105081427', N'Các cách tối ưu hoá Google Ads', 'https://img.youtube.com/vi/sample4.jpg', 20000, N'Tối ưu chi phí và hiệu quả khi sử dụng Google Ads', 1),
('20241106150315', N'Hướng dẫn Photoshop cho người mới', 'https://img.youtube.com/vi/sample5.jpg', 99000, N'Bắt đầu với những kỹ thuật cơ bản trong Photoshop', 1),
('20241107182210', N'Giới thiệu Node.js cơ bản', 'https://img.youtube.com/vi/sample6.jpg', 67000, N'Khóa học lập trình Node.js cho người mới', 1),
('20241108093320', N'Quy trình phát triển phần mềm Agile', 'https://img.youtube.com/vi/sample7.jpg', 48000, N'Tìm hiểu về mô hình phát triển Agile', 1),
('20241109020640', N'Thủ thuật sử dụng Google Docs', 'https://img.youtube.com/vi/sample8.jpg', 52000, N'Thủ thuật giúp làm việc với Google Docs hiệu quả', 1),
('20241110134551', N'Cách viết CV thu hút nhà tuyển dụng', 'https://img.youtube.com/vi/sample9.jpg', 82000, N'Hướng dẫn viết CV chi tiết và thu hút', 1),
('20241111152344', N'Tìm hiểu về Machine Learning', 'https://img.youtube.com/vi/sample10.jpg', 43000, N'Giới thiệu về lĩnh vực Machine Learning', 1),
('20241112073818', N'Thế giới động vật kỳ thú', 'https://img.youtube.com/vi/sample11.jpg', 150000, N'Khám phá những điều thú vị về thế giới động vật', 1),
('20241113090314', N'Cuộc sống ở thành phố Tokyo', 'https://img.youtube.com/vi/sample12.jpg', 61000, N'Trải nghiệm cuộc sống tại Tokyo', 1),
('20241114110630', N'Hướng dẫn sử dụng Canva cho thiết kế', 'https://img.youtube.com/vi/sample13.jpg', 47000, N'Tạo các thiết kế đơn giản với Canva', 1),
('20241115112719', N'Cách quản lý thời gian hiệu quả', 'https://img.youtube.com/vi/sample14.jpg', 56000, N'Kỹ năng quản lý thời gian cho học sinh và sinh viên', 1),
('20241116123955', N'Học Tiếng Anh qua bài hát', 'https://img.youtube.com/vi/sample15.jpg', 125000, N'Phương pháp học tiếng Anh qua các bài hát', 1),
('20241117071709', N'Sử dụng SQL trong phân tích dữ liệu', 'https://img.youtube.com/vi/sample16.jpg', 91000, N'Áp dụng SQL vào phân tích dữ liệu', 1),
('20241118054433', N'Hướng dẫn sử dụng PowerPoint nâng cao', 'https://img.youtube.com/vi/sample17.jpg', 85000, N'Tạo bài thuyết trình chuyên nghiệp với PowerPoint', 1),
('20241119181921', N'Các bước lập kế hoạch kinh doanh', 'https://img.youtube.com/vi/sample18.jpg', 37000, N'Hướng dẫn từng bước để lập kế hoạch kinh doanh', 1),
('20241120041329', N'Tạo dựng kênh YouTube thành công', 'https://img.youtube.com/vi/sample19.jpg', 133000, N'Chia sẻ kinh nghiệm phát triển kênh YouTube', 1),
('20241121091422', N'Kỹ năng thuyết trình tự tin', 'https://img.youtube.com/vi/sample20.jpg', 99000, N'Mẹo giúp bạn thuyết trình tự tin và hiệu quả', 1);

INSERT INTO FAVORITE (ID, USERID, VIDEOID, LIKEDATE) VALUES
('20241103010522', '20241103070122', '20241103060110', '2024-11-01'),
('20241104020830', '20241103120115', '20241104073433', '2024-11-02'),
('20241105021219', '20241104150608', '20241104120609', '2024-11-03'),
('20241106081844', '20241105235945', '20241105081427', '2024-11-04'),
('20241107031713', '20241106112411', '20241106150315', '2024-11-05'),
('20241108092126', '20241107181737', '20241107182210', '2024-11-06'),
('20241109041535', '20241108004519', '20241108093320', '2024-11-07'),
('20241110060708', '20241108060122', '20241109020640', '2024-11-08'),
('20241111131014', '20241109122356', '20241110134551', '2024-11-09'),
('20241112101442', '20241110020412', '20241111152344', '2024-11-10'),
('20241113020311', '20241103070122', '20241112073818', '2024-11-11'),
('20241114131752', '20241103070122', '20241113090314', '2024-11-12');
/*
('20241115042903', '20241113095416', '20241114110630', '2024-11-13'),
('20241116083115', '20241114050751', '20241115112719', '2024-11-14'),
('20241117150624', '20241115032544', '20241116123955', '2024-11-15'),
('20241118030235', '20241116142836', '20241117071709', '2024-11-16'),
('20241119041257', '20241117191322', '20241118054433', '2024-11-17'),
('20241120071201', '20241118071910', '20241119181921', '2024-11-18'),
('20241121101948', '20241119124558', '20241120041329', '2024-11-19'),
('20241122010336', '20241120065832', '20241121091422', '2024-11-20');
*/

INSERT INTO SHARE (ID, USERID, VIDEOID, EMAILS, SHAREDATE) VALUES
('20241103080102', '20241103070122', '20241104073433', 'friend1@gmail.com', '2024-11-01'),
('20241104091515', '20241103120115', '20241104120609', 'friend2@gmail.com', '2024-11-02'),
('20241105112306', '20241104150608', '20241105081427', 'friend3@gmail.com', '2024-11-03'),
('20241106130429', '20241105235945', '20241106150315', 'friend4@gmail.com', '2024-11-04'),
('20241107081109', '20241106112411', '20241107182210', 'friend5@gmail.com', '2024-11-05'),
('20241108051245', '20241107181737', '20241108093320', 'friend6@gmail.com', '2024-11-06'),
('20241109021437', '20241108004519', '20241109020640', 'friend7@gmail.com', '2024-11-07'),
('20241110032918', '20241108060122', '20241110134551', 'friend8@gmail.com', '2024-11-08'),
('20241111142101', '20241109122356', '20241111152344', 'friend9@gmail.com', '2024-11-09'),
('20241112160753', '20241110020412', '20241112073818', 'friend10@gmail.com', '2024-11-10');
/*
('20241113120832', '20241111032223', '20241113090314', 'friend11@gmail.com', '2024-11-11'),
('20241114120217', '20241112080845', '20241114110630', 'friend12@gmail.com', '2024-11-12'),
('20241115090346', '20241113095416', '20241115112719', 'friend13@gmail.com', '2024-11-13'),
('20241116120324', '20241114050751', '20241116123955', 'friend14@gmail.com', '2024-11-14'),
('20241117051931', '20241115032544', '20241117071709', 'friend15@gmail.com', '2024-11-15'),
('20241118063423', '20241116142836', '20241118054433', 'friend16@gmail.com', '2024-11-16'),
('20241119121249', '20241117191322', '20241119181921', 'friend17@gmail.com', '2024-11-17'),
('20241120080756', '20241118071910', '20241120041329', 'friend18@gmail.com', '2024-11-18'),
('20241121110215', '20241119124558', '20241121091422', 'friend19@gmail.com', '2024-11-19'),
('20241122020604', '20241120065832', '20241103060110', 'friend20@gmail.com', '2024-11-20');
*/

SELECT TITLE FROM FAVORITE F, VIDEO V WHERE VIDEOID = V.ID;

SELECT * FROM VIDEO V, FAVORITE F WHERE F.VIDEOID = V.ID AND F.USERID = '20241103070122';