INSERT INTO role (type) VALUES (0), (1);
INSERT INTO event (event_name, event_type) VALUES ('Корпаратив', 'CORPORATE'), ('Футбол', 'SPORT'), ('ЧГК', 'INTELLECTUAL'), ('Своя Игра', 'INTELLECTUAL'), ('Встреча', 'NOT_FORMAL');
INSERT INTO employee (name, surname, login, password, role_id) VALUES ('Vlad', 'Naumkin', 'v.naumkin', '1234', 1), ('Ruslan', 'Kondratyev', 'r.kondratyev', '1234', 1), ('Dima', 'Anikin', 'd.anikin', '1234', 1), ('Alexander', 'Dolganov', 'a.dolganov', '1234', 1), ('Denis', 'Telyatnikov', 'd.telyatnikov', '1234', 2), ('test', 'test', 'test', 'test', 1);
INSERT INTO calendar_event (name, description, date, min_number) VALUES ('Hackaton', 'This is super event', '2022-08-20', 0), ('Hackaton', 'This is super event', '2022-08-21', 0), ('Shafut', 'This is flip calendar event', '2022-09-03', 1), ('Football', 'This is sport event', '2022-09-07', 10);
INSERT INTO comment (user_name, message, date_time, calendar_event_id) VALUES ('Vlad Naumkin', 'Hello', '2022-09-14 12:30:30', 1);