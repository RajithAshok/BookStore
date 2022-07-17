CREATE TABLE IF NOT EXISTS books (
  isbn char(10) NOT NULL DEFAULT '',
  author varchar(100) NOT NULL,
  title varchar(128) NOT NULL,
  price decimal(7,2) NOT NULL,
  subject varchar(30) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS cart (
  userid varchar(20) NOT NULL DEFAULT '',
  isbn char(10) NOT NULL DEFAULT '',
  qty int NOT NULL
) ENGINE=InnoDB;

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
) ENGINE=InnoDB;

INSERT INTO members (fname, lname, address, city, state, zip, phone, email, userid, password, creditcardtype, creditcardnumber) VALUES
('rajith', 'ashok', 'sahakarnagar', 'bengaluru', 'karnataka', 560092, '9876543210', 'rajith@gmail.com', 'rajith', 'rajith', '', '1234123412341234'),
('adhin', 'alex', 'devinnagar', 'bengaluru', 'karnataka', 560094, '9182736451', 'adhin@gmail.com', 'adhin', 'adhin', '', '9876987698769876');

CREATE TABLE IF NOT EXISTS odetails (
  ono int NOT NULL DEFAULT '0',
  isbn char(10) NOT NULL DEFAULT '',
  qty int NOT NULL,
  price decimal(7,2) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS orders (
  userid varchar(20) NOT NULL,
  ono int NOT NULL DEFAULT '0',
  received date NOT NULL,
  shipped date DEFAULT NULL,
  shipAddress varchar(50) DEFAULT NULL,
  shipCity varchar(30) DEFAULT NULL,
  shipState varchar(20) DEFAULT NULL,
  shipZip int DEFAULT NULL
) ENGINE=InnoDB;

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
ADD CONSTRAINT cart_ibfk_1 FOREIGN KEY (userid) REFERENCES members (userid),
ADD CONSTRAINT cart_ibfk_2 FOREIGN KEY (isbn) REFERENCES books (isbn);

ALTER TABLE odetails
ADD CONSTRAINT odetails_ibfk_1 FOREIGN KEY (ono) REFERENCES orders (ono),
ADD CONSTRAINT odetails_ibfk_2 FOREIGN KEY (isbn) REFERENCES books (isbn);

ALTER TABLE orders
ADD CONSTRAINT orders_ibfk_1 FOREIGN KEY (userid) REFERENCES members (userid);