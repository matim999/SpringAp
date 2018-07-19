CREATE SEQUENCE public.rolee_rolee_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE public.rolee_rolee_id_seq OWNER TO postgres;

CREATE TABLE public.staff_rolee (
    staff_id smallint NOT NULL,
    rolee_id smallint NOT NULL
);

ALTER TABLE public.staff_rolee OWNER TO postgres;

CREATE TABLE public.rolee (
    rolee_id integer DEFAULT nextval('public.rolee_rolee_id_seq'::regclass) NOT NULL,
    rolee character varying(60) NOT NULL
);

ALTER TABLE ONLY public.rolee
    ADD CONSTRAINT rolee_pkey PRIMARY KEY (rolee_id);

ALTER TABLE public.rolee OWNER TO postgres;

ALTER TABLE ONLY public.staff_rolee
    ADD CONSTRAINT staff_rolee_pkey PRIMARY KEY (rolee_id, staff_id);

ALTER TABLE ONLY public.staff_rolee
    ADD CONSTRAINT staff_rolee_rolee_id_fkey FOREIGN KEY (rolee_id) REFERENCES public.rolee(rolee_id) ON UPDATE CASCADE ON DELETE RESTRICT;


ALTER TABLE ONLY public.staff_rolee
    ADD CONSTRAINT staff_rolee_staff_id_fkey FOREIGN KEY (staff_id) REFERENCES public.staff(staff_id) ON UPDATE CASCADE ON DELETE RESTRICT;

INSERT INTO public.rolee VALUES (1, 'Role1');
INSERT INTO public.rolee VALUES (2, 'Role2');
INSERT INTO public.rolee VALUES (3, 'Role3');
INSERT INTO public.rolee VALUES (4, 'Role4');


INSERT INTO public.staff_rolee VALUES (1, 1);
INSERT INTO public.staff_rolee VALUES (1, 2);
INSERT INTO public.staff_rolee VALUES (2, 1);