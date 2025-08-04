create table if not exists posts (
    id serial primary key,
    description varchar not null,
    path_to_file varchar,
    user_id int references users(id)
);