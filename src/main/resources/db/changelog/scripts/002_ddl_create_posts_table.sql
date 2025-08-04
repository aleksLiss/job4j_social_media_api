create table if not exists posts(
    id serial primary key,
    title varchar not null,
    description varchar not null,
    path_to_file varchar
);