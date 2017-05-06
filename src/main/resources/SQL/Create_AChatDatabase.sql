CREATE TABLE account (
	account_id integer PRIMARY KEY AUTOINCREMENT,
	nickname varchar(30) NOT NULL,
	email varchar(254) NOT NULL UNIQUE,
	pass_hash varchar(32) NOT NULL
);

CREATE TABLE friends_pair (
	first_friend_id integer NOT NULL,
	second_friend_id integer NOT NULL,
    FOREIGN KEY (first_friend_id) REFERENCES account (account_id),
    FOREIGN KEY (second_friend_id) REFERENCES account (account_id)
);

CREATE TABLE conversation (
	convers_id integer PRIMARY KEY AUTOINCREMENT,
	name varchar(30) NOT NULL,
	creator_id integer NOT NULL,
    FOREIGN KEY (creator_id) REFERENCES account (account_id)
);

CREATE TABLE message (
	message_id integer PRIMARY KEY AUTOINCREMENT,
	msg_text text NOT NULL,
	from_id integer NOT NULL,
	convers_id integer NOT NULL,
    FOREIGN KEY (from_id) REFERENCES account (account_id),
    FOREIGN KEY (convers_id) REFERENCES conversation (conver_id)
);

CREATE TABLE convers_member (
	convers_id integer NOT NULL,
	member_id integer NOT NULL,
	FOREIGN KEY (member_id) REFERENCES account (account_id),
    FOREIGN KEY (convers_id) REFERENCES conversation (convers_id)
);