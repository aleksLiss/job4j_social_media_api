create table if not exists friends (
    id serial primary key,
    from_user_id int references users(id),
    to_user_id int references users(id)
);