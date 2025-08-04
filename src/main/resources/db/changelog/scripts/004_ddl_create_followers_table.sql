create table if not exists followers(
    id serial primary key,
    following_user_id int references users(id),
    followers_user_id int references  users(id)
);