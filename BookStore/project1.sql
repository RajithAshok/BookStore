CREATE TABLE IF NOT EXISTS books (
  isbn char(10) NOT NULL DEFAULT '',
  author varchar(100) NOT NULL,
  title varchar(128) NOT NULL,
  price decimal(7,2) NOT NULL,
  subject varchar(30) NOT NULL
);

CREATE TABLE IF NOT EXISTS cart (
  userid varchar(20) NOT NULL DEFAULT '',
  isbn char(10) NOT NULL DEFAULT '',
  qty int NOT NULL
);

CREATE TABLE IF NOT EXISTS members (
  fname varchar(20) NOT NULL,
  lname varchar(20) NOT NULL,
  address varchar(50) NOT NULL,
  city varchar(30) NOT NULL,
  state varchar(20) NOT NULL,
  zip int NOT NULL,
  phone varchar(12) DEFAULT NULL,
  email varchar(40) DEFAULT NULL,
  userid varchar(20) NOT NULL DEFAULT '',
  password varchar(20) DEFAULT NULL,
  creditcardtype varchar(10) DEFAULT NULL,
  creditcardnumber char(16) DEFAULT NULL
);

INSERT INTO members (fname, lname, address, city, state, zip, phone, email, userid, password, creditcardtype, creditcardnumber) VALUES
('rajith', 'ashok', 'sahakarnagar', 'bengaluru', 'karnataka', 560092, '9876543210', 'rajith@gmail.com', 'rajith', 'rajith', '', '1234123412341234'),
('adhin', 'alex', 'devinnagar', 'bengaluru', 'karnataka', 560094, '9182736451', 'adhin@gmail.com', 'adhin', 'adhin', '', '9876987698769876');

CREATE TABLE IF NOT EXISTS odetails (
  ono int NOT NULL DEFAULT '0',
  isbn char(10) NOT NULL DEFAULT '',
  qty int NOT NULL,
  price decimal(7,2) NOT NULL
);

CREATE TABLE IF NOT EXISTS orders (
  userid varchar(20) NOT NULL,
  ono int NOT NULL DEFAULT '0',
  received date NOT NULL,
  shipped date DEFAULT NULL,
  shipAddress varchar(50) DEFAULT NULL,
  shipCity varchar(30) DEFAULT NULL,
  shipState varchar(20) DEFAULT NULL,
  shipZip int DEFAULT NULL
);

ALTER TABLE books
 ADD PRIMARY KEY (isbn);

ALTER TABLE cart
 ADD PRIMARY KEY (userid,isbn), ADD KEY isbn (isbn);

ALTER TABLE members
 ADD PRIMARY KEY (userid);

ALTER TABLE odetails
 ADD PRIMARY KEY (ono, isbn), ADD KEY isbn (isbn);

ALTER TABLE orders
 ADD PRIMARY KEY (ono), ADD KEY userid (userid);

ALTER TABLE cart
ADD FOREIGN KEY (userid) REFERENCES members (userid),
ADD FOREIGN KEY (isbn) REFERENCES books (isbn);

ALTER TABLE odetails
ADD FOREIGN KEY (ono) REFERENCES orders (ono),
ADD FOREIGN KEY (isbn) REFERENCES books (isbn);

ALTER TABLE orders
ADD FOREIGN KEY (userid) REFERENCES members (userid);

-- Book entry
insert into books values(1000, "John Green", "The Fault in Our Stars", 288.00, "Young Adult Fiction");
insert into books values(1001, "Earnest Cline", "Ready PLayer One", 345.00, "Sci-Fi");
insert into books values(1002, "Harper Lee", "To Kill a Mocking Bird", 245.00, "Southern Gothic Fiction");
insert into books values(1003, "Stephen Hawking", "The Theory of Everthing", 198.00, "Science");
insert into books values(1004, "James Dashner", "The Maze Runner", 301.00, "Young Adult Fiction");
insert into books values(1005, "John Green", "The Abundance of Katherines", 299.00, "Young Adult Fiction");
insert into books values(1006, "Nikola Tesla", "The Strange Life of Nikola Tesla", 509.00, "Biography");
insert into books values(1007, "Neil DeGrasse Tyson", "Cosmic Queries: Star Talks", 1152.00, "Astronomy");
insert into books values(1008, "Patrick Johnson", "The Physics of Star Wars", 869.00, "Film Criticism");
insert into books values(1009, "John Green", "Looking for Atlanta", 236.00, "Young Adult Fiction");
insert into books values(1010, "John Green", "The fault in our stars", 540.00, "Romance");
insert into books values(1011,"john green","paper town",320.00,"young adult fiction");
insert into books values(1012,"John Green","Turtles all the way down",456.00,"young adult fiction");
insert into books values(1013,"Patrick Johnson","Black Queer studies",500.00,"biography");
insert into books values(1014,"Patrick Johnson","Sweet Tea",120.00,"romantic play");
insert into books values(1015,"Abdul Kalam","Wings of fire",600.00,"Autobiography");
insert into books values(1016,"Ruskin Bond","The perfect murder",450.00,"Mystery");
insert into books values(1017,"Ruskin Bond","The house of stranger stories",330.00,"Mystery");
insert into books values(1018,"John Bunyan","Pilgrim's Progress",150.00,"autobiography");
insert into books values(1019,"Robinson Crusoe","Daniel Defoe",220.00,"tragic rom-com");