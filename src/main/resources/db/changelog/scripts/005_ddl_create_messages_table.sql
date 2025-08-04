create table if not exists messages(
    id serial primary key,
    content text,
    from_user_id int references users(id),
    to_user_id int references users(id)
);