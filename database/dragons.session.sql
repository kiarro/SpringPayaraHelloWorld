-- reset tables and sequences
-- DELETE FROM dragons;
-- DELETE FROM caves;

-- ALTER SEQUENCE dragons_dragon_id_seq RESTART WITH 1;
-- ALTER SEQUENCE caves_cave_id_seq RESTART WITH 1;



-- SELECT c.relname FROM pg_class c WHERE c.relkind = 'S';

SELECT * FROM dragons;

-- ALTER TABLE dragons ALTER COLUMN name TYPE varchar(100);

-- DROP TABLE dragons;
-- DROP TABLE caves;

-- with ins1 as (
--     insert into caves(depth, number_of_treasures) 
--     values (4864.4844, 678855.2798074303) 
--     RETURNING cave_id as new_cave_id 
-- ) 
-- insert into dragons (name, coordinate_x, coordinate_y, creation_date, creation_date_zone, age, weight, type, character, cave_id)
-- select 'Mjolmiark, Doomed Doom of Water', -47.10009, 29.884247, '01-01-2020', 'Z', 33125, 67137, 'WATER', 'WISE', new_cave_id from ins1 
-- returning dragon_id