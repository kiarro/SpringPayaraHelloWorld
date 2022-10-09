--
-- PostgreSQL database dump
--

-- Dumped from database version 14.2
-- Dumped by pg_dump version 14.2

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: caves; Type: TABLE; Schema: public; Owner: dragon
--

CREATE TABLE public.caves (
    cave_id bigint NOT NULL,
    depth real NOT NULL,
    number_of_treasures double precision NOT NULL,
    CONSTRAINT chk_noft CHECK ((number_of_treasures > (0)::double precision))
);


ALTER TABLE public.caves OWNER TO dragon;

--
-- Name: caves_cave_id_seq; Type: SEQUENCE; Schema: public; Owner: dragon
--

CREATE SEQUENCE public.caves_cave_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.caves_cave_id_seq OWNER TO dragon;

--
-- Name: caves_cave_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: dragon
--

ALTER SEQUENCE public.caves_cave_id_seq OWNED BY public.caves.cave_id;


--
-- Name: dragons; Type: TABLE; Schema: public; Owner: dragon
--

CREATE TABLE public.dragons (
    dragon_id bigint NOT NULL,
    name character varying(100) NOT NULL,
    coordinate_x real NOT NULL,
    coordinate_y real NOT NULL,
    creation_date timestamp with time zone NOT NULL,
    creation_date_zone character varying(50),
    age bigint NOT NULL,
    weight bigint NOT NULL,
    type character varying(30) NOT NULL,
    "character" character varying(30) NOT NULL,
    cave_id bigint NOT NULL,
    CONSTRAINT chk_age CHECK ((age > 0)),
    CONSTRAINT chk_drid CHECK ((dragon_id > 0)),
    CONSTRAINT chk_name CHECK ((NOT ((name)::text = ''::text))),
    CONSTRAINT chk_weight CHECK ((weight > 0))
);


ALTER TABLE public.dragons OWNER TO dragon;

--
-- Name: dragons_dragon_id_seq; Type: SEQUENCE; Schema: public; Owner: dragon
--

CREATE SEQUENCE public.dragons_dragon_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.dragons_dragon_id_seq OWNER TO dragon;

--
-- Name: dragons_dragon_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: dragon
--

ALTER SEQUENCE public.dragons_dragon_id_seq OWNED BY public.dragons.dragon_id;


--
-- Name: caves cave_id; Type: DEFAULT; Schema: public; Owner: dragon
--

ALTER TABLE ONLY public.caves ALTER COLUMN cave_id SET DEFAULT nextval('public.caves_cave_id_seq'::regclass);


--
-- Name: dragons dragon_id; Type: DEFAULT; Schema: public; Owner: dragon
--

ALTER TABLE ONLY public.dragons ALTER COLUMN dragon_id SET DEFAULT nextval('public.dragons_dragon_id_seq'::regclass);


--
-- Data for Name: caves; Type: TABLE DATA; Schema: public; Owner: dragon
--

COPY public.caves (cave_id, depth, number_of_treasures) FROM stdin;
1	4864.4844	678855.2798074303
2	5377.6206	866743.5726829324
4	6274.9316	227788.69131056784
5	7593.8164	296956.737929291
6	130.19206	406689.03860117204
7	2846.342	168740.12612917574
8	6143.2847	13476.917750864906
9	2365.0378	913419.7095281392
10	9607.543	650450.0342601095
11	9575.515	934749.2630268964
12	6680.17	836529.6115760993
13	91.16236	528978.4134029737
14	287.17276	259349.6696929049
15	4318.755	543987.8901686361
16	3514.1719	80284.37765341981
17	4772.051	723691.1694348007
18	5444.39	868447.9018194039
19	8830.463	145518.29234436873
20	2993.7888	548776.5157074195
21	727.0414	209828.09412807133
22	6289.197	564400.7538219864
23	3395.094	989297.179322377
24	6643.443	720285.7245472345
25	394.91068	334212.9431186856
26	4000.6245	628397.5087070902
29	4864.4844	678855.2798074303
30	4864.4844	678855.2798074303
3	500	300000
\.


--
-- Data for Name: dragons; Type: TABLE DATA; Schema: public; Owner: dragon
--

COPY public.dragons (dragon_id, name, coordinate_x, coordinate_y, creation_date, creation_date_zone, age, weight, type, "character", cave_id) FROM stdin;
1	Mjolmiark, Doomed Doom of Water	29.884247	-47.10009	2022-06-09 19:01:57.29+03	Europe/Moscow	33125	67137	WATER	WISE	1
2	Crumirier, Disaster of the Holy Shadows	14.226463	64.70347	2022-06-09 19:07:57.403+03	Europe/Moscow	82598	42347	FIRE	WISE	2
4	Merzermur, Wise Sultan of Dream	-13.920373	-16.163897	2022-06-09 19:12:10.74+03	Europe/Moscow	32088	21924	FIRE	CHAOTIC_EVIL	4
5	Brearced, Wise Father of Fire	-52.481594	47.98225	2022-06-09 19:12:10.856+03	Europe/Moscow	4045	82021	AIR	CUNNING	5
6	Morloriarc, Great Protector of Sun	-70.39864	29.35552	2022-06-09 19:12:10.861+03	Europe/Moscow	89240	60081	FIRE	CHAOTIC_EVIL	6
7	Lukslamarure, the Worlds General	-89.37455	-34.16079	2022-06-09 19:12:10.975+03	Europe/Moscow	73635	16150	AIR	WISE	7
8	Reiairzur, the Moon Prime	-24.29656	84.89247	2022-06-09 19:12:10.98+03	Europe/Moscow	82159	47870	UNDERGROUND	CUNNING	8
9	Creoarkzur, the Order Ancestor	-82.46358	-67.06795	2022-06-09 19:12:11.097+03	Europe/Moscow	92357	84217	UNDERGROUND	WISE	9
10	Drakuraker, Disaster of Void	-48.02256	-35.527218	2022-06-09 19:12:11.103+03	Europe/Moscow	40463	61109	AIR	WISE	10
11	Jarzermed, the Doom Master	58.892456	-64.94267	2022-06-09 19:12:11.215+03	Europe/Moscow	16768	10578	FIRE	CHAOTIC	11
12	Jarairer, Prime of the Sealed Lord	4.01877	10.433928	2022-06-09 19:12:11.221+03	Europe/Moscow	37338	56509	FIRE	FICKLE	12
13	Zedmeezur, the Sealed Water	-28.264818	-36.585354	2022-06-09 19:12:11.333+03	Europe/Moscow	30877	17791	AIR	FICKLE	13
14	Crumaracmur, Protector of the Silver One	-76.19068	9.6550255	2022-06-09 19:12:11.341+03	Europe/Moscow	14170	59300	AIR	CUNNING	14
15	Mjolmid, the Good Night	94.76109	-22.459148	2022-06-09 19:12:11.464+03	Europe/Moscow	19260	7647	FIRE	CHAOTIC	15
16	Jagsalmard, Eater of Dream	-19.82899	39.465706	2022-06-09 19:12:11.472+03	Europe/Moscow	34771	60222	FIRE	CUNNING	16
17	Crumirider, the Moon General	-91.04537	-13.362641	2022-06-09 19:12:11.603+03	Europe/Moscow	50465	7617	UNDERGROUND	CHAOTIC_EVIL	17
18	Madsalmarmur, Slayer of Fire	12.670964	-12.121397	2022-06-09 19:12:11.608+03	Europe/Moscow	19533	5798	FIRE	FICKLE	18
19	Zurmeemur, One of the Magical Lord	-61.835712	-83.31986	2022-06-09 19:12:11.736+03	Europe/Moscow	14792	52157	AIR	FICKLE	19
20	Drakcloer, the Moon Magician	-91.94855	64.64191	2022-06-09 19:12:11.744+03	Europe/Moscow	89768	29363	WATER	FICKLE	20
21	Azurarkark, the Dead Death	-68.47602	-98.283455	2022-06-09 19:12:11.856+03	Europe/Moscow	73852	52407	UNDERGROUND	CHAOTIC	21
22	Krredmed, the Bright Lord	-15.360082	35.25898	2022-06-09 19:12:11.861+03	Europe/Moscow	77706	75496	AIR	WISE	22
23	Azurclozur, Sage of the Sealed Doom	-56.29336	89.71875	2022-06-09 19:12:11.974+03	Europe/Moscow	87340	79546	AIR	FICKLE	23
24	Rayarkarc, the Wind Patriarch	-29.32645	-59.21603	2022-06-09 19:12:11.979+03	Europe/Moscow	59739	43380	WATER	WISE	24
25	Crumeeder, the Great Earth	-86.05706	-83.52326	2022-06-09 19:12:12.095+03	Europe/Moscow	2678	9367	FIRE	WISE	25
26	Luksalmarure, Master of the Horrible Overlord	67.0552	-45.429787	2022-06-09 19:12:12.11+03	Europe/Moscow	45616	26598	WATER	CUNNING	26
29	Mjolmiark, Doomed Doom of Water	29.884247	-47.10009	2022-06-13 20:00:22.632+03	Europe/Moscow	33125	10000	WATER	WISE	29
30	Mjolmiark, Doomed Master of Water	29.884247	-47.10009	2022-06-13 20:25:22.398+03	Europe/Moscow	33125	10000	WATER	WISE	30
3	ExcellentDrake	20.02	11.333	2022-06-09 19:12:10.736+03	Europe/Moscow	701	3011	AIR	WISE	3
\.


--
-- Name: caves_cave_id_seq; Type: SEQUENCE SET; Schema: public; Owner: dragon
--

SELECT pg_catalog.setval('public.caves_cave_id_seq', 30, true);


--
-- Name: dragons_dragon_id_seq; Type: SEQUENCE SET; Schema: public; Owner: dragon
--

SELECT pg_catalog.setval('public.dragons_dragon_id_seq', 30, true);


--
-- Name: caves caves_pkey; Type: CONSTRAINT; Schema: public; Owner: dragon
--

ALTER TABLE ONLY public.caves
    ADD CONSTRAINT caves_pkey PRIMARY KEY (cave_id);


--
-- Name: dragons dragons_pkey; Type: CONSTRAINT; Schema: public; Owner: dragon
--

ALTER TABLE ONLY public.dragons
    ADD CONSTRAINT dragons_pkey PRIMARY KEY (dragon_id);


--
-- Name: dragons fk_cave; Type: FK CONSTRAINT; Schema: public; Owner: dragon
--

ALTER TABLE ONLY public.dragons
    ADD CONSTRAINT fk_cave FOREIGN KEY (cave_id) REFERENCES public.caves(cave_id);


--
-- PostgreSQL database dump complete
--

