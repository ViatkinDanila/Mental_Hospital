INSERT INTO mental_hospital.user_roles (id, name) VALUES (1, 'USER');
INSERT INTO mental_hospital.user_roles (id, name) VALUES (2, 'ADMIN');
INSERT INTO mental_hospital.user_roles (id, name) VALUES (3, 'DOCTOR');

INSERT INTO mental_hospital.users (id, user_role_id, email, password, first_name, last_name, number, is_banned) VALUES (1, 1, 'sucker@mail.ru', 'suckerasd', 'sucker', 'sss', 'sss', 0);
INSERT INTO mental_hospital.users (id, user_role_id, email, password, first_name, last_name, number, is_banned) VALUES (2, 3, 'lilice@mail.ru', '86f344855ebaec97a9e2d54843e2bf06457ea9c1ac2ad0921c14ff2c2e5f788f7ba4729bbfc5a9bd32c21e34a7ce28aa91e5148cfdfb04c436c5d06be644994a', 'Zaharov', 'Eduard', 'asd', 0);
INSERT INTO mental_hospital.users (id, user_role_id, email, password, first_name, last_name, number, is_banned) VALUES (4, 3, 'bookmaker@gmail.com', '86f344855ebaec97a9e2d54843e2bf06457ea9c1ac2ad0921c14ff2c2e5f788f7ba4729bbfc5a9bd32c21e34a7ce28aa91e5148cfdfb04c436c5d06be644994a', 'Danila', 'Viatkin', '+123123123123123', 0);
INSERT INTO mental_hospital.users (id, user_role_id, email, password, first_name, last_name, number, is_banned) VALUES (6, 3, 'bookmaker@sucker.com', '86f344855ebaec97a9e2d54843e2bf06457ea9c1ac2ad0921c14ff2c2e5f788f7ba4729bbfc5a9bd32c21e34a7ce28aa91e5148cfdfb04c436c5d06be644994a', 'Danila', 'Viatkin', '+123123123123123', 0);
INSERT INTO mental_hospital.users (id, user_role_id, email, password, first_name, last_name, number, is_banned) VALUES (7, 1, 'user@gmail.com', '86f344855ebaec97a9e2d54843e2bf06457ea9c1ac2ad0921c14ff2c2e5f788f7ba4729bbfc5a9bd32c21e34a7ce28aa91e5148cfdfb04c436c5d06be644994a', 'USER', 'USER', '+123123123123123', 0);
INSERT INTO mental_hospital.users (id, user_role_id, email, password, first_name, last_name, number, is_banned) VALUES (8, 1, 'privit@mail.ru', '5110a9c4d3ebae27e1c745f5420e994c8ff4d21a8cf7582c9782cdfaef90106ec3553602f2bc880e81b7f34f9ea7b6b7bd433649fc5c29fd400b10ef0c22c7ae', 'Alex', 'Varashilov', '+14881441881111', 0);
INSERT INTO mental_hospital.users (id, user_role_id, email, password, first_name, last_name, number, is_banned) VALUES (9, 2, 'admin@admin.ru', '5110a9c4d3ebae27e1c745f5420e994c8ff4d21a8cf7582c9782cdfaef90106ec3553602f2bc880e81b7f34f9ea7b6b7bd433649fc5c29fd400b10ef0c22c7ae', 'admin', 'admin', '+111111111111111', 0);
INSERT INTO mental_hospital.users (id, user_role_id, email, password, first_name, last_name, number, is_banned) VALUES (12, 1, 'user@mail.ru', '86f344855ebaec97a9e2d54843e2bf06457ea9c1ac2ad0921c14ff2c2e5f788f7ba4729bbfc5a9bd32c21e34a7ce28aa91e5148cfdfb04c436c5d06be644994a', 'User', 'User', '+37529123123123', 0);
INSERT INTO mental_hospital.users (id, user_role_id, email, password, first_name, last_name, number, is_banned) VALUES (13, 3, 'doctor@mail.ru', '5110a9c4d3ebae27e1c745f5420e994c8ff4d21a8cf7582c9782cdfaef90106ec3553602f2bc880e81b7f34f9ea7b6b7bd433649fc5c29fd400b10ef0c22c7ae', 'Doctor', 'Doctor', '+1222222222222', 0);
INSERT INTO mental_hospital.users (id, user_role_id, email, password, first_name, last_name, number, is_banned) VALUES (14, 1, 'useruser@gmail.com', '86f344855ebaec97a9e2d54843e2bf06457ea9c1ac2ad0921c14ff2c2e5f788f7ba4729bbfc5a9bd32c21e34a7ce28aa91e5148cfdfb04c436c5d06be644994a', 'Dada', 'Dada', '+123123123123', 0);
INSERT INTO mental_hospital.users (id, user_role_id, email, password, first_name, last_name, number, is_banned) VALUES (15, 1, 'user123@mail.ru', '86f344855ebaec97a9e2d54843e2bf06457ea9c1ac2ad0921c14ff2c2e5f788f7ba4729bbfc5a9bd32c21e34a7ce28aa91e5148cfdfb04c436c5d06be644994a', 'nett', 'nett', '+123123123123123', 0);

INSERT INTO mental_hospital.patient_cards (id, user_id, spare_number, age, sex) VALUES (1, 1, 'asd', 13, 'yes');
INSERT INTO mental_hospital.patient_cards (id, user_id, spare_number, age, sex) VALUES (3, 4, '+123123123123123', 18, 'yes');
INSERT INTO mental_hospital.patient_cards (id, user_id, spare_number, age, sex) VALUES (5, 6, '+123123123123123', 28, 'yes');
INSERT INTO mental_hospital.patient_cards (id, user_id, spare_number, age, sex) VALUES (6, 7, '+123123123123123', 54, 'USER');
INSERT INTO mental_hospital.patient_cards (id, user_id, spare_number, age, sex) VALUES (7, 8, '+123123123123123', 20, 'Male');
INSERT INTO mental_hospital.patient_cards (id, user_id, spare_number, age, sex) VALUES (8, 12, '+37529123123321', 18, 'Male');
INSERT INTO mental_hospital.patient_cards (id, user_id, spare_number, age, sex) VALUES (9, 14, '+123123123123', 18, 'Male');
INSERT INTO mental_hospital.patient_cards (id, user_id, spare_number, age, sex) VALUES (10, 15, '+123123123123123', 18, 'Male');

INSERT INTO mental_hospital.drugs (id, name) VALUES (1, 'Antipsychotics');
INSERT INTO mental_hospital.drugs (id, name) VALUES (2, 'Tranquillisers');
INSERT INTO mental_hospital.drugs (id, name) VALUES (3, 'Antidepressants');
INSERT INTO mental_hospital.drugs (id, name) VALUES (4, 'Нейролептики');
INSERT INTO mental_hospital.drugs (id, name) VALUES (5, 'Транквилизаторы');
INSERT INTO mental_hospital.drugs (id, name) VALUES (6, 'Антидипрессанты');

INSERT INTO mental_hospital.diseases (id, name, description) VALUES (1, 'Биполярное расстройство', 'Биполярное расстройство, известное так же, как маниакально-депрессивный психоз, является психическим заболеванием, которое характеризуется нетипичной сменой настроений, перепадами энергетического уровня и способности функционировать. IN MY EYES');
INSERT INTO mental_hospital.diseases (id, name, description) VALUES (2, 'Депрессия ', 'Под депрессией понимают расстройство психического здоровья тогда, когда наблюдаются продолжительное подавленное настроение и другие симптомы, затрагивающие мысли, чувства, поведение и весь организм. Когда в повседневной жизни люди говорят о депрессии, под этим словом может пониматься множество проблем.');
INSERT INTO mental_hospital.diseases (id, name, description) VALUES (3, 'Бессонница', 'Бессонница — это расстройство сна, которое характеризуется его недостаточной продолжительностью, неудовлетворенностью его качеством, невозможностью заснуть или сочетанием этих факторов на протяжении долгого времени.');
INSERT INTO mental_hospital.diseases (id, name, description) VALUES (4, 'Depression ', 'Грусть');



INSERT INTO mental_hospital.treatment_courses (id, instructions) VALUES (1, '');
INSERT INTO mental_hospital.treatment_courses (id, instructions) VALUES (30, 'how di ho');
INSERT INTO mental_hospital.treatment_courses (id, instructions) VALUES (31, 'Пейте больше воды');

INSERT INTO mental_hospital.drug_recipes (treatment_course_id, drug_id, description, dose) VALUES (30, 3, 'posle edi', 123);
INSERT INTO mental_hospital.drug_recipes (treatment_course_id, drug_id, description, dose) VALUES (31, 3, 'За 2 часа до сна.', 12);


INSERT INTO mental_hospital.doctor_info (doctor_id, specialization, work_experience, classification) VALUES (2, 'Мастер по вкручиваниям', 20, 4);

INSERT INTO mental_hospital.disease_symptoms (treatment_course_id, diseases_id, symptoms) VALUES (30, 4, 'Понижение работоспособности.');
INSERT INTO mental_hospital.disease_symptoms (treatment_course_id, diseases_id, symptoms) VALUES (31, 4, 'Понижение работоспособности.');

INSERT INTO mental_hospital.consultations (id, doctor_id, patient_id, treatment_course_id, communication_type, date, duration, price, status) VALUES (16, 2, 6, 30, 'ONLINE', '2022-01-31 00:00:00', 45, 0, 'COMPLETED');
INSERT INTO mental_hospital.consultations (id, doctor_id, patient_id, treatment_course_id, communication_type, date, duration, price, status) VALUES (17, 13, 6, null, 'ONLINE', '2022-01-31 00:00:00', 0, 0, 'PENDING');
INSERT INTO mental_hospital.consultations (id, doctor_id, patient_id, treatment_course_id, communication_type, date, duration, price, status) VALUES (18, 13, 6, null, 'ONLINE', '2022-01-31 00:00:00', 0, 0, 'PENDING');
INSERT INTO mental_hospital.consultations (id, doctor_id, patient_id, treatment_course_id, communication_type, date, duration, price, status) VALUES (19, 2, 9, null, 'ONLINE', '2022-01-31 00:00:00', 0, 0, 'REJECTED');
INSERT INTO mental_hospital.consultations (id, doctor_id, patient_id, treatment_course_id, communication_type, date, duration, price, status) VALUES (20, 2, 9, null, 'ONLINE', '2022-01-31 00:00:00', 0, 0, 'PENDING');
INSERT INTO mental_hospital.consultations (id, doctor_id, patient_id, treatment_course_id, communication_type, date, duration, price, status) VALUES (21, 2, 10, null, 'ONLINE', '2022-01-31 00:00:00', 0, 0, 'REJECTED');
INSERT INTO mental_hospital.consultations (id, doctor_id, patient_id, treatment_course_id, communication_type, date, duration, price, status) VALUES (22, 2, 10, 31, 'ONLINE', '2022-01-31 00:00:00', 45, 0, 'COMPLETED');



