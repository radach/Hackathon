create database hackathon;

create table user(id integer PRIMARY KEY,
    email varchar(50),
    password varchar(50),
    name varchar(100),
    balanceWork integer,
    balanceFun integer,
    bio varchar(255),
    floor integer
);

create table history(id integer PRIMARY KEY,
    ownerId integer,
    submitTime date,
    FOREIGN KEY (ownerId) REFERENCES user(id)
);

create table donation(
    id integer PRIMARY KEY,
    description varchar(255),
    targetId integer,
    amount integer,
    FOREIGN KEY (id) REFERENCES history(id),
    FOREIGN KEY (targetId) REFERENCES user(id)
);

create table task(
    id integer PRIMARY KEY,
    name varchar(50),
    description varchar(255),
    FOREIGN KEY (id) REFERENCES history(id)
);

create table auction(
    id integer PRIMARY KEY,
    name varchar(50),
    maxCredit integer,
    timeoutSec integer,
    FOREIGN KEY (id) REFERENCES history(id)
);

create table bid(
    id integer PRIMARY KEY,
    auctionId integer,
    bidderId integer,
    amount integer,
    submitTime date,
    FOREIGN KEY (auctionId) REFERENCES auction(id),
    FOREIGN KEY (bidderId) REFERENCES user(id)
);
