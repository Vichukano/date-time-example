create table if not exists test_table(
    id bigserial primary key,
    date_time timestamptz not null default current_timestamp
);