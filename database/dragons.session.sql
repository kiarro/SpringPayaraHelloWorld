-- -- reset tables and sequences
-- DELETE FROM dragons;
-- DELETE FROM caves;

-- ALTER SEQUENCE dragons_dragon_id_seq RESTART WITH 1;
-- ALTER SEQUENCE caves_cave_id_seq RESTART WITH 1;



-- SELECT c.relname FROM pg_class c WHERE c.relkind = 'S';

SELECT * FROM dragons;

-- ALTER TABLE dragons ALTER COLUMN name TYPE varchar(100);