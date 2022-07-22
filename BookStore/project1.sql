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



ALTER TABLE books
 ADD PRIMARY KEY (isbn);

ALTER TABLE cart
 ADD PRIMARY KEY (userid,isbn), ADD KEY isbn (isbn);

ALTER TABLE members
 ADD PRIMARY KEY (userid);

ALTER TABLE cart
ADD FOREIGN KEY (userid) REFERENCES members (userid),
ADD FOREIGN KEY (isbn) REFERENCES books (isbn);


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
insert into books values(1011, "john green", "paper town", 320.00, "young adult fiction");
insert into books values(1012, "John Green", "Turtles all the way down", 456.00, "young adult fiction");
insert into books values(1013, "Patrick Johnson", "Black Queer studies", 500.00, "Biography");
insert into books values(1014, "Patrick Johnson", "Sweet Tea", 120.00, "Romance");
insert into books values(1015, "Abdul Kalam", "Wings of fire", 600.00, "Autobiography");
insert into books values(1016, "Ruskin Bond", "The perfect murder", 450.00, "Mystery");
insert into books values(1017, "Ruskin Bond", "The house of stranger stories", 330.00, "Mystery");
insert into books values(1018, "John Bunyan", "Pilgrim's Progress", 150.00, "Autobiography");
insert into books values(1019, "Robinson Crusoe", "Daniel Defoe", 220.00, "tragic rom-com");
insert into books values(1020, "Trevor Noah", "Born a Crime", 349.00, "Autobiography");
insert into books values(1021, "Paulo Coelho", "The Alchemist", 175.00, "Adventure Fiction");
insert into books values(1022, "Chetan Bhagat", "Five Point Someone", 147.00, "Fiction");
insert into books values(1023, "Chetan Bhagat", "Half Girlfriend", 182.00, "Romance");
insert into books values(1024, "Robyn Carr", "Virgin River", 608.72, "Romance");
insert into books values(1025, "Robyn Carr", "Shelter Mountain", 1827.78, "Romance");
insert into books values(1026, "Robyn Carr", "A Virgin River Christmas", 1784.87, "Romance");
insert into books values(1027, "Amor Towles", "A Gentleman in Moscow", 1861.00, "Historical fiction");
insert into books values(1028, "Amor Towles", "The Book of Lost Names", 1105.00, "Historical fiction");
insert into books values(1029, "Kristin Harmel", "The Forest of Vanishing Stars", 1658.00, "War");
insert into books values(1030, "Kristin Harmel", "The Life Intended", 847.00, "Fiction");
insert into books values(1031,"Bret Easton Ellis","Lunar Park",380.00,"Fiction");
insert into books values(1032,"Ray Bradbury","Fahrenheit 451",290.00,"Classics");
insert into books values(1033,"Chuck Palahniuk","Invisible Monsters",340.00,"Fiction");
insert into books values(1034,"Irvine Welsh","Skagboys",260.00,"Thriller");
insert into books values(1035,"Anthony burgess","A clockwork orange",300.00,"Science Fiction");
insert into books values(1036,"	Irvine welsh","trainspotting",320.00,"Fiction");
insert into books values(1037,"Iain Banks","The wasp factory",340.00,"Horror");
insert into books values(1038,"R.D Ronald","The Elephant tree",280.00,"Fiction");
insert into books values(1039,"Chuvk Palakniuk","Fight Club",360.00,"Classics");
insert into books values(1040,"Bret Easton Ellis","American Psycho",290.00,"Fiction");
