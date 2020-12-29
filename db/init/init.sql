CREATE TABLE users (
	id bigserial NOT NULL,
	email varchar(255) NULL,
	"password" varchar(255) NULL,
	username varchar(255) NULL,
	CONSTRAINT uk6dotkott2kjsp8vw4d0m25fb7 UNIQUE (email),
	CONSTRAINT ukr43af9ap4edm43mmtq01oddj6 UNIQUE (username),
	CONSTRAINT users_pkey PRIMARY KEY (id)
);

CREATE TABLE roles (
	id serial NOT NULL,
	"name" varchar(20) NULL,
	CONSTRAINT roles_pkey PRIMARY KEY (id)
);

CREATE TABLE user_roles (
	user_id int8 NOT NULL,
	role_id int4 NOT NULL,
	CONSTRAINT user_roles_pkey PRIMARY KEY (user_id, role_id),
	CONSTRAINT fkh8ciramu9cc9q3qcqiv4ue8a6 FOREIGN KEY (role_id) REFERENCES roles(id),
	CONSTRAINT fkhfh9dx7w3ubf1co1vdev94g3f FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE public.record (
	id serial NOT NULL,
	"timestamp" timestamp NULL,
	user_id int8 NULL,
	CONSTRAINT record_pkey PRIMARY KEY (id),
	CONSTRAINT fk44ctj7m4iik9qhcbaqt1aynka FOREIGN KEY (user_id) REFERENCES users(id)
);

INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_MODERATOR');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');