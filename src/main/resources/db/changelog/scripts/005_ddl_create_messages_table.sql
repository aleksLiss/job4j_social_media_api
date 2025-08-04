create table if not exists messages (
    id serial primary key,
    content varchar not null,
    from_user_id int references users(id),
    to_user_id int references users(id)
);