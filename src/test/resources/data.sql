INSERT INTO public.country (country_id, country) VALUES (country_country_id_seq.nextval, 'Afghanistan');
INSERT INTO public.country (country_id, country) VALUES (country_country_id_seq.nextval, 'Algeria');

INSERT INTO public.actor (actor_id, first_name, last_name) VALUES (actor_actor_id_seq.nextval, 'Penelope', 'Guiness');
INSERT INTO public.actor (actor_id, first_name, last_name) VALUES (actor_actor_id_seq.nextval, 'Nick', 'Wahlberg');

INSERT INTO public.category (category_id, name) VALUES (category_category_id_seq.nextval, 'Action');
INSERT INTO public.category (category_id, name) VALUES (category_category_id_seq.nextval, 'Animation');

INSERT INTO public.city (city_id, city, country_id) VALUES (city_city_id_seq.nextval, 'A Corua (La Corua)', 2);
INSERT INTO public.city (city_id, city, country_id) VALUES (city_city_id_seq.nextval, 'Abu Dhabi', 1);

INSERT INTO public.address (address_id, address, address2, district, city_id, postal_code, phone) VALUES (address_address_id_seq.nextval, '47 MySakila Drive', null, 'Alberta', 2, '', '');
INSERT INTO public.address (address_id, address, address2, district, city_id, postal_code, phone) VALUES (address_address_id_seq.nextval, '28 MySQL Boulevard', null, 'QLD', 1, '', '');

INSERT INTO public.customer (customer_id, store_id, first_name, last_name, email, address_id, activebool, create_date, active) VALUES (customer_customer_id_seq.nextval, 2, 'Jared', 'Ely', 'jared.ely@sakilacustomer.org', 1, true, '2006-02-14', 1);
INSERT INTO public.customer (customer_id, store_id, first_name, last_name, email, address_id, activebool, create_date, active) VALUES (customer_customer_id_seq.nextval, 1, 'Mary', 'Smith', 'mary.smith@sakilacustomer.org', 2, true, '2006-02-14', 1);
INSERT INTO public.customer (customer_id, store_id, first_name, last_name, email, address_id, activebool, create_date, active) VALUES (customer_customer_id_seq.nextval, 1, 'Linda', 'Williams', 'linda.williams@sakilacustomer.org', 2, true, '2006-02-14', 1);
INSERT INTO public.customer (customer_id, store_id, first_name, last_name, email, address_id, activebool, create_date, active) VALUES (customer_customer_id_seq.nextval, 2, 'Barbara', 'Jones', 'barbara.jones@sakilacustomer.org', 1, true, '2006-02-14', 1);

INSERT INTO public.language (language_id, name) VALUES (language_language_id_seq.nextval, 'English             ');
INSERT INTO public.language (language_id, name) VALUES (language_language_id_seq.nextval, 'Italian             ');

INSERT INTO public.film (film_id, title, description, release_year, language_id, rental_duration, rental_rate, length, replacement_cost) VALUES (film_film_id_seq.nextval, 'Chamber Italian', 'A Fateful Reflection of a Moose And a Husband who must Overcome a Monkey in Nigeria', 2006, 1, 7, 4.99, 117, 14.99);
INSERT INTO public.film (film_id, title, description, release_year, language_id, rental_duration, rental_rate, length, replacement_cost) VALUES (film_film_id_seq.nextval, 'Grosse Wonderful', 'A Epic Drama of a Cat And a Explorer who must Redeem a Moose in Australia', 2006, 2, 5, 4.99, 49, 19.99);
INSERT INTO public.film (film_id, title, description, release_year, language_id, rental_duration, rental_rate, length, replacement_cost) VALUES (film_film_id_seq.nextval, 'Adaptation Holes', 'A Astounding Reflection of a Lumberjack And a Car who must Sink a Lumberjack in A Baloon Factory', 2006, 1, 7, 2.99, 50, 18.99);
INSERT INTO public.film (film_id, title, description, release_year, language_id, rental_duration, rental_rate, length, replacement_cost) VALUES (film_film_id_seq.nextval, 'Affair Prejudice', 'A Fanciful Documentary of a Frisbee And a Lumberjack who must Chase a Monkey in A Shark Tank', 2006, 2, 5, 2.99, 117, 26.99);
INSERT INTO public.film (film_id, title, description, release_year, language_id, rental_duration, rental_rate, length, replacement_cost) VALUES (film_film_id_seq.nextval, 'African Egg', 'A Fast-Paced Documentary of a Pastry Chef And a Dentist who must Pursue a Forensic Psychologist in The Gulf of Mexico', 2006, 1, 6, 2.99, 130, 22.99);
INSERT INTO public.film (film_id, title, description, release_year, language_id, rental_duration, rental_rate, length, replacement_cost) VALUES (film_film_id_seq.nextval, 'Agent Truman', 'A Intrepid Panorama of a Robot And a Boy who must Escape a Sumo Wrestler in Ancient China', 2006, 2, 3, 2.99, 169, 17.99);


INSERT INTO public.inventory (inventory_id, film_id, store_id) VALUES (/*1*/inventory_inventory_id_seq.nextval, 1, 1);
INSERT INTO public.inventory (inventory_id, film_id, store_id) VALUES (/*2*/inventory_inventory_id_seq.nextval, 1, 2);
INSERT INTO public.inventory (inventory_id, film_id, store_id) VALUES (/*3*/inventory_inventory_id_seq.nextval, 1, 2);
INSERT INTO public.inventory (inventory_id, film_id, store_id) VALUES (/*4*/inventory_inventory_id_seq.nextval, 2, 2);
INSERT INTO public.inventory (inventory_id, film_id, store_id) VALUES (/*5*/inventory_inventory_id_seq.nextval, 2, 1);
INSERT INTO public.inventory (inventory_id, film_id, store_id) VALUES (/*6*/inventory_inventory_id_seq.nextval, 3, 1);
INSERT INTO public.inventory (inventory_id, film_id, store_id) VALUES (/*7*/inventory_inventory_id_seq.nextval, 4, 2);
INSERT INTO public.inventory (inventory_id, film_id, store_id) VALUES (/*8*/inventory_inventory_id_seq.nextval, 4, 2);
INSERT INTO public.inventory (inventory_id, film_id, store_id) VALUES (/*9*/inventory_inventory_id_seq.nextval, 5, 2);
INSERT INTO public.inventory (inventory_id, film_id, store_id) VALUES (/*10*/inventory_inventory_id_seq.nextval, 6, 1);

INSERT INTO public.staff (staff_id, first_name, last_name, address_id, email, active, username, password) VALUES (staff_staff_id_seq.nextval, 'Mike', 'Hillyer', 1, 'Mike.Hillyer@sakilastaff.com', true, 'Mike', '8cb2237d0679ca88db6464eac60da96345513964');
INSERT INTO public.staff (staff_id, first_name, last_name, address_id, email, active, username, password) VALUES (staff_staff_id_seq.nextval, 'Jon', 'Stephens', 2, 'Jon.Stephens@sakilastaff.com', true, 'Jon', '8cb2237d0679ca88db6464eac60da96345513964');

INSERT INTO public.store (store_id, address_id) VALUES (store_store_id_seq.nextval, 1);
INSERT INTO public.store (store_id, address_id) VALUES (store_store_id_seq.nextval, 2);


INSERT INTO public.rental (rental_id, rental_date, inventory_id, customer_id, return_date, staff_id) VALUES (rental_rental_id_seq.nextval, '2005-05-24 22:54:33.000000', 1, 2, '2005-05-28 19:40:33.000000', 1);
INSERT INTO public.rental (rental_id, rental_date, inventory_id, customer_id, return_date, staff_id) VALUES (rental_rental_id_seq.nextval, '2005-05-24 23:03:39.000000', 2, 1, '2005-06-01 22:12:39.000000', 2);
INSERT INTO public.rental (rental_id, rental_date, inventory_id, customer_id, return_date, staff_id) VALUES (rental_rental_id_seq.nextval, '2018-07-19 20:54:33.000000', 6, 2, null, 1);
INSERT INTO public.rental (rental_id, rental_date, inventory_id, customer_id, return_date, staff_id) VALUES (rental_rental_id_seq.nextval, '2018-06-24 23:03:39.000000', 2, 1, null, 2);


INSERT INTO public.payment (payment_id, customer_id, staff_id, rental_id, amount, payment_date) VALUES (payment_payment_id_seq.nextval, 1, 2, 1, 7.99, '2007-02-15 22:25:46.996577');
INSERT INTO public.payment (payment_id, customer_id, staff_id, rental_id, amount, payment_date) VALUES (payment_payment_id_seq.nextval, 2, 1, 2, 1.99, '2007-02-16 17:23:14.996577');

INSERT INTO public.film_actor (actor_id, film_id) VALUES (1, 2);
INSERT INTO public.film_actor (actor_id, film_id) VALUES (2, 1);

INSERT INTO public.film_category (film_id, category_id) VALUES (1, 2);
INSERT INTO public.film_category (film_id, category_id) VALUES (2, 1);
