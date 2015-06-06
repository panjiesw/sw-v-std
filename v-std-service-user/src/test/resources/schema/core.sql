CREATE TABLE std_role
(
  id bigserial NOT NULL,
  name character varying(16) NOT NULL,
  description text,
  CONSTRAINT std_role_pkey PRIMARY KEY (id),
  CONSTRAINT unique_name UNIQUE (name)
);

CREATE TABLE std_user
(
  id bigserial NOT NULL,
  username character varying(32) NOT NULL,
  email character varying(32) NOT NULL,
  password character varying(128) NOT NULL,
  fullname character varying(128),
  status smallint NOT NULL DEFAULT 2,
  reset_token text,
  created_at bigint NOT NULL DEFAULT extract(EPOCH from CURRENT_TIMESTAMP)::BIGINT * 1000,
  updated_at bigint,
  last_login_at bigint,
  role_id bigint NOT NULL DEFAULT 2,
  CONSTRAINT std_user_pkey PRIMARY KEY (id),
  CONSTRAINT lnk_std_role_std_user FOREIGN KEY (role_id)
      REFERENCES std_role (id) MATCH FULL
      ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT unique_email UNIQUE (email),
  CONSTRAINT unique_username UNIQUE (username)
);

CREATE INDEX index_role_id
  ON std_user
  USING btree
  (role_id);

CREATE INDEX index_status
  ON std_user
  USING btree
  (status);

INSERT INTO std_role (name, description) VALUES
  ('ADMIN', 'Administrative Role'),
  ('USER', 'Common User Role');

INSERT INTO std_user
(username, email, password, fullname, status, reset_token, role_id) VALUES
  ('admin', 'panjie@panjiesw.com', '', 'Panjie Setiawan Wicaksono', 1, '', 1);
