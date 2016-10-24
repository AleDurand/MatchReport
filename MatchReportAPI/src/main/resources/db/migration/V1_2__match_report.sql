-- USER --
INSERT INTO match_report.user (username, password, enabled) VALUES ('adurand', 'password', 1);
INSERT INTO match_report.user (username, password, enabled) VALUES ('fcuenca', 'password', 1);
INSERT INTO match_report.user (username, password, enabled) VALUES ('username1', 'password', 1);
INSERT INTO match_report.user (username, password, enabled) VALUES ('username2', 'password', 0);

-- AUTHORITY --
INSERT INTO match_report.authority (id, name) VALUES (1, 'Administrador');
INSERT INTO match_report.authority (id, name) VALUES (2, 'User');

-- USER HAS AUTHORITY --
INSERT INTO match_report.user_has_authority (user_id, authority_id) VALUES ('adurand', 1);
INSERT INTO match_report.user_has_authority (user_id, authority_id) VALUES ('fcuenca', 1);
INSERT INTO match_report.user_has_authority (user_id, authority_id) VALUES ('username1', 2);
INSERT INTO match_report.user_has_authority (user_id, authority_id) VALUES ('username2', 2);

-- CANCHA --
INSERT INTO match_report.cancha (id, nombre, direccion, foto) VALUES (1, 'Bombonera', 'Barrio La Boca', NULL);
INSERT INTO match_report.cancha (id, nombre, direccion, foto) VALUES (2, 'Monumental', 'Barrio Nuñez', NULL);
INSERT INTO match_report.cancha (id, nombre, direccion, foto) VALUES (3, 'Nuevo gasometro', 'Barrio Bajo Flores', NULL);
INSERT INTO match_report.cancha (id, nombre, direccion, foto) VALUES (4, 'Cilindro de avellaneda', 'Barrio Avellaneda', NULL);

-- CLUB --
INSERT INTO match_report.club (id, nombre, direccion, escudo, pagina_web, id_cancha) VALUES (1, 'Boca Juniors', 'Barrio La Boca', NULL, 'www.bj.com.ar', 1);
INSERT INTO match_report.club (id, nombre, direccion, escudo, pagina_web, id_cancha) VALUES (2, 'River Plate', 'Barrio Nuñez', NULL, 'www.rp.com.ar', 2);
INSERT INTO match_report.club (id, nombre, direccion, escudo, pagina_web, id_cancha) VALUES (3, 'San Lorenzo', 'Barrio Bajo Flores', NULL, 'www.sl.com.ar', 3);
INSERT INTO match_report.club (id, nombre, direccion, escudo, pagina_web, id_cancha) VALUES (4, 'Racing Club', 'Barrio Avellaneda', NULL, 'www.rc.com.ar', 4);

-- TORNEO --
INSERT INTO match_report.torneo (id, fecha_inicio, fecha_fin) VALUES (1, '2016-01-01', '2016-06-30');
INSERT INTO match_report.torneo (id, fecha_inicio, fecha_fin) VALUES (2, '2016-07-01', '2016-12-31	');

-- FECHA --
INSERT INTO match_report.fecha (id, numero, descripcion, torneo_id) VALUES (1, 1, 'Fecha 1 - Apertura 2016', 1);
INSERT INTO match_report.fecha (id, numero, descripcion, torneo_id) VALUES (2, 2, 'Fecha 2 - Apertura 2016', 1);
INSERT INTO match_report.fecha (id, numero, descripcion, torneo_id) VALUES (3, 3, 'Fecha 3 - Apertura 2016', 1);
INSERT INTO match_report.fecha (id, numero, descripcion, torneo_id) VALUES (4, 4, 'Fecha 4 - Apertura 2016', 1);
INSERT INTO match_report.fecha (id, numero, descripcion, torneo_id) VALUES (5, 5, 'Fecha 5 - Apertura 2016', 1);
INSERT INTO match_report.fecha (id, numero, descripcion, torneo_id) VALUES (6, 6, 'Fecha 6 - Apertura 2016', 1);
INSERT INTO match_report.fecha (id, numero, descripcion, torneo_id) VALUES (7, 1, 'Fecha 1 - Clausura 2016', 2);
INSERT INTO match_report.fecha (id, numero, descripcion, torneo_id) VALUES (8, 2, 'Fecha 2 - Clausura 2016', 2);
INSERT INTO match_report.fecha (id, numero, descripcion, torneo_id) VALUES (9, 3, 'Fecha 3 - Clausura 2016', 2);
INSERT INTO match_report.fecha (id, numero, descripcion, torneo_id) VALUES (10, 4, 'Fecha 4 - Clausura 2016', 2);
INSERT INTO match_report.fecha (id, numero, descripcion, torneo_id) VALUES (11, 5, 'Fecha 5 - Clausura 2016', 2);
INSERT INTO match_report.fecha (id, numero, descripcion, torneo_id) VALUES (12, 6, 'Fecha 6 - Clausura 2016', 2);
-- JUGADOR --
INSERT INTO match_report.jugador (id, nombres, apellido, fecha_nacimiento, status, tipo_documento, nro_documento) VALUES (1, '', 'Boca 1', '1990-01-01', 0, 'DNI', 10000000);
INSERT INTO match_report.jugador (id, nombres, apellido, fecha_nacimiento, status, tipo_documento, nro_documento) VALUES (2, '', 'Boca 2', '1990-01-01', 0, 'DNI', 10000000);
INSERT INTO match_report.jugador (id, nombres, apellido, fecha_nacimiento, status, tipo_documento, nro_documento) VALUES (3, '', 'Boca 3', '1990-01-01', 0, 'DNI', 10000000);
INSERT INTO match_report.jugador (id, nombres, apellido, fecha_nacimiento, status, tipo_documento, nro_documento) VALUES (4, '', 'Boca 4', '1990-01-01', 0, 'DNI', 10000000);
INSERT INTO match_report.jugador (id, nombres, apellido, fecha_nacimiento, status, tipo_documento, nro_documento) VALUES (5, '', 'Boca 5', '1990-01-01', 0, 'DNI', 10000000);
INSERT INTO match_report.jugador (id, nombres, apellido, fecha_nacimiento, status, tipo_documento, nro_documento) VALUES (6, '', 'Boca 6', '1990-01-01', 0, 'DNI', 10000000);
INSERT INTO match_report.jugador (id, nombres, apellido, fecha_nacimiento, status, tipo_documento, nro_documento) VALUES (7, '', 'Boca 7', '1990-01-01', 0, 'DNI', 10000000);
INSERT INTO match_report.jugador (id, nombres, apellido, fecha_nacimiento, status, tipo_documento, nro_documento) VALUES (8, '', 'Boca 8', '1990-01-01', 0, 'DNI', 10000000);
INSERT INTO match_report.jugador (id, nombres, apellido, fecha_nacimiento, status, tipo_documento, nro_documento) VALUES (9, '', 'Boca 9', '1990-01-01', 0, 'DNI', 10000000);
INSERT INTO match_report.jugador (id, nombres, apellido, fecha_nacimiento, status, tipo_documento, nro_documento) VALUES (10, '', 'Boca 10', '1990-01-01', 0, 'DNI', 10000000);
INSERT INTO match_report.jugador (id, nombres, apellido, fecha_nacimiento, status, tipo_documento, nro_documento) VALUES (11, '', 'Boca 11', '1990-01-01', 0, 'DNI', 10000000);

INSERT INTO match_report.jugador (id, nombres, apellido, fecha_nacimiento, status, tipo_documento, nro_documento) VALUES (12, '', 'River 1', '1990-01-01', 0, 'DNI', 20000000);
INSERT INTO match_report.jugador (id, nombres, apellido, fecha_nacimiento, status, tipo_documento, nro_documento) VALUES (13, '', 'River 2', '1990-01-01', 0, 'DNI', 20000000);
INSERT INTO match_report.jugador (id, nombres, apellido, fecha_nacimiento, status, tipo_documento, nro_documento) VALUES (14, '', 'River 3', '1990-01-01', 0, 'DNI', 20000000);
INSERT INTO match_report.jugador (id, nombres, apellido, fecha_nacimiento, status, tipo_documento, nro_documento) VALUES (15, '', 'River 4', '1990-01-01', 0, 'DNI', 20000000);
INSERT INTO match_report.jugador (id, nombres, apellido, fecha_nacimiento, status, tipo_documento, nro_documento) VALUES (16, '', 'River 5', '1990-01-01', 0, 'DNI', 20000000);
INSERT INTO match_report.jugador (id, nombres, apellido, fecha_nacimiento, status, tipo_documento, nro_documento) VALUES (17, '', 'River 6', '1990-01-01', 0, 'DNI', 20000000);
INSERT INTO match_report.jugador (id, nombres, apellido, fecha_nacimiento, status, tipo_documento, nro_documento) VALUES (18, '', 'River 7', '1990-01-01', 0, 'DNI', 20000000);
INSERT INTO match_report.jugador (id, nombres, apellido, fecha_nacimiento, status, tipo_documento, nro_documento) VALUES (19, '', 'River 8', '1990-01-01', 0, 'DNI', 20000000);
INSERT INTO match_report.jugador (id, nombres, apellido, fecha_nacimiento, status, tipo_documento, nro_documento) VALUES (20, '', 'River 9', '1990-01-01', 0, 'DNI', 20000000);
INSERT INTO match_report.jugador (id, nombres, apellido, fecha_nacimiento, status, tipo_documento, nro_documento) VALUES (21, '', 'River 10', '1990-01-01', 0, 'DNI', 20000000);
INSERT INTO match_report.jugador (id, nombres, apellido, fecha_nacimiento, status, tipo_documento, nro_documento) VALUES (22, '', 'River 11', '1990-01-01', 0, 'DNI', 20000000);

INSERT INTO match_report.jugador (id, nombres, apellido, fecha_nacimiento, status, tipo_documento, nro_documento) VALUES (23, '', 'San Lorenzo 1', '1990-01-01', 0, 'DNI', 10000000);
INSERT INTO match_report.jugador (id, nombres, apellido, fecha_nacimiento, status, tipo_documento, nro_documento) VALUES (24, '', 'San Lorenzo 2', '1990-01-01', 0, 'DNI', 10000000);
INSERT INTO match_report.jugador (id, nombres, apellido, fecha_nacimiento, status, tipo_documento, nro_documento) VALUES (25, '', 'San Lorenzo 3', '1990-01-01', 0, 'DNI', 10000000);
INSERT INTO match_report.jugador (id, nombres, apellido, fecha_nacimiento, status, tipo_documento, nro_documento) VALUES (26, '', 'San Lorenzo 4', '1990-01-01', 0, 'DNI', 10000000);
INSERT INTO match_report.jugador (id, nombres, apellido, fecha_nacimiento, status, tipo_documento, nro_documento) VALUES (27, '', 'San Lorenzo 5', '1990-01-01', 0, 'DNI', 10000000);
INSERT INTO match_report.jugador (id, nombres, apellido, fecha_nacimiento, status, tipo_documento, nro_documento) VALUES (28, '', 'San Lorenzo 6', '1990-01-01', 0, 'DNI', 10000000);
INSERT INTO match_report.jugador (id, nombres, apellido, fecha_nacimiento, status, tipo_documento, nro_documento) VALUES (29, '', 'San Lorenzo 7', '1990-01-01', 0, 'DNI', 10000000);
INSERT INTO match_report.jugador (id, nombres, apellido, fecha_nacimiento, status, tipo_documento, nro_documento) VALUES (30, '', 'San Lorenzo 8', '1990-01-01', 0, 'DNI', 10000000);
INSERT INTO match_report.jugador (id, nombres, apellido, fecha_nacimiento, status, tipo_documento, nro_documento) VALUES (31, '', 'San Lorenzo 9', '1990-01-01', 0, 'DNI', 10000000);
INSERT INTO match_report.jugador (id, nombres, apellido, fecha_nacimiento, status, tipo_documento, nro_documento) VALUES (32, '', 'San Lorenzo 10', '1990-01-01', 0, 'DNI', 10000000);
INSERT INTO match_report.jugador (id, nombres, apellido, fecha_nacimiento, status, tipo_documento, nro_documento) VALUES (33, '', 'San Lorenzo 11', '1990-01-01', 0, 'DNI', 10000000);

INSERT INTO match_report.jugador (id, nombres, apellido, fecha_nacimiento, status, tipo_documento, nro_documento) VALUES (34, '', 'Racing 1', '1990-01-01', 0, 'DNI', 20000000);
INSERT INTO match_report.jugador (id, nombres, apellido, fecha_nacimiento, status, tipo_documento, nro_documento) VALUES (35, '', 'Racing 2', '1990-01-01', 0, 'DNI', 20000000);
INSERT INTO match_report.jugador (id, nombres, apellido, fecha_nacimiento, status, tipo_documento, nro_documento) VALUES (36, '', 'Racing 3', '1990-01-01', 0, 'DNI', 20000000);
INSERT INTO match_report.jugador (id, nombres, apellido, fecha_nacimiento, status, tipo_documento, nro_documento) VALUES (37, '', 'Racing 4', '1990-01-01', 0, 'DNI', 20000000);
INSERT INTO match_report.jugador (id, nombres, apellido, fecha_nacimiento, status, tipo_documento, nro_documento) VALUES (38, '', 'Racing 5', '1990-01-01', 0, 'DNI', 20000000);
INSERT INTO match_report.jugador (id, nombres, apellido, fecha_nacimiento, status, tipo_documento, nro_documento) VALUES (39, '', 'Racing 6', '1990-01-01', 0, 'DNI', 20000000);
INSERT INTO match_report.jugador (id, nombres, apellido, fecha_nacimiento, status, tipo_documento, nro_documento) VALUES (40, '', 'Racing 7', '1990-01-01', 0, 'DNI', 20000000);
INSERT INTO match_report.jugador (id, nombres, apellido, fecha_nacimiento, status, tipo_documento, nro_documento) VALUES (41, '', 'Racing 8', '1990-01-01', 0, 'DNI', 20000000);
INSERT INTO match_report.jugador (id, nombres, apellido, fecha_nacimiento, status, tipo_documento, nro_documento) VALUES (42, '', 'Racing 9', '1990-01-01', 0, 'DNI', 20000000);
INSERT INTO match_report.jugador (id, nombres, apellido, fecha_nacimiento, status, tipo_documento, nro_documento) VALUES (43, '', 'Racing 10', '1990-01-01', 0, 'DNI', 20000000);
INSERT INTO match_report.jugador (id, nombres, apellido, fecha_nacimiento, status, tipo_documento, nro_documento) VALUES (44, '', 'Racing 11', '1990-01-01', 0, 'DNI', 20000000);

-- JUEGA EN --
INSERT INTO match_report.juega_en (id_jugador, id_club, id_torneo) VALUES (1, 1, 1);
INSERT INTO match_report.juega_en (id_jugador, id_club, id_torneo) VALUES (2, 1, 1);
INSERT INTO match_report.juega_en (id_jugador, id_club, id_torneo) VALUES (3, 1, 1);
INSERT INTO match_report.juega_en (id_jugador, id_club, id_torneo) VALUES (4, 1, 1);
INSERT INTO match_report.juega_en (id_jugador, id_club, id_torneo) VALUES (5, 1, 1);
INSERT INTO match_report.juega_en (id_jugador, id_club, id_torneo) VALUES (6, 1, 1);
INSERT INTO match_report.juega_en (id_jugador, id_club, id_torneo) VALUES (7, 1, 1);
INSERT INTO match_report.juega_en (id_jugador, id_club, id_torneo) VALUES (8, 1, 1);
INSERT INTO match_report.juega_en (id_jugador, id_club, id_torneo) VALUES (9, 1, 1);
INSERT INTO match_report.juega_en (id_jugador, id_club, id_torneo) VALUES (10, 1, 1);
INSERT INTO match_report.juega_en (id_jugador, id_club, id_torneo) VALUES (11, 1, 1);

INSERT INTO match_report.juega_en (id_jugador, id_club, id_torneo) VALUES (1, 1, 2);
INSERT INTO match_report.juega_en (id_jugador, id_club, id_torneo) VALUES (2, 1, 2);
INSERT INTO match_report.juega_en (id_jugador, id_club, id_torneo) VALUES (3, 1, 2);
INSERT INTO match_report.juega_en (id_jugador, id_club, id_torneo) VALUES (4, 1, 2);
INSERT INTO match_report.juega_en (id_jugador, id_club, id_torneo) VALUES (5, 1, 2);
INSERT INTO match_report.juega_en (id_jugador, id_club, id_torneo) VALUES (6, 1, 2);
INSERT INTO match_report.juega_en (id_jugador, id_club, id_torneo) VALUES (7, 1, 2);
INSERT INTO match_report.juega_en (id_jugador, id_club, id_torneo) VALUES (8, 1, 2);
INSERT INTO match_report.juega_en (id_jugador, id_club, id_torneo) VALUES (9, 1, 2);
INSERT INTO match_report.juega_en (id_jugador, id_club, id_torneo) VALUES (10, 1, 2);
INSERT INTO match_report.juega_en (id_jugador, id_club, id_torneo) VALUES (11, 1, 2);

INSERT INTO match_report.juega_en (id_jugador, id_club, id_torneo) VALUES (12, 2, 1);
INSERT INTO match_report.juega_en (id_jugador, id_club, id_torneo) VALUES (13, 2, 1);
INSERT INTO match_report.juega_en (id_jugador, id_club, id_torneo) VALUES (14, 2, 1);
INSERT INTO match_report.juega_en (id_jugador, id_club, id_torneo) VALUES (15, 2, 1);
INSERT INTO match_report.juega_en (id_jugador, id_club, id_torneo) VALUES (16, 2, 1);
INSERT INTO match_report.juega_en (id_jugador, id_club, id_torneo) VALUES (17, 2, 1);
INSERT INTO match_report.juega_en (id_jugador, id_club, id_torneo) VALUES (18, 2, 1);
INSERT INTO match_report.juega_en (id_jugador, id_club, id_torneo) VALUES (19, 2, 1);
INSERT INTO match_report.juega_en (id_jugador, id_club, id_torneo) VALUES (20, 2, 1);
INSERT INTO match_report.juega_en (id_jugador, id_club, id_torneo) VALUES (21, 2, 1);
INSERT INTO match_report.juega_en (id_jugador, id_club, id_torneo) VALUES (22, 2, 1);

INSERT INTO match_report.juega_en (id_jugador, id_club, id_torneo) VALUES (12, 2, 2);
INSERT INTO match_report.juega_en (id_jugador, id_club, id_torneo) VALUES (13, 2, 2);
INSERT INTO match_report.juega_en (id_jugador, id_club, id_torneo) VALUES (14, 2, 2);
INSERT INTO match_report.juega_en (id_jugador, id_club, id_torneo) VALUES (15, 2, 2);
INSERT INTO match_report.juega_en (id_jugador, id_club, id_torneo) VALUES (16, 2, 2);
INSERT INTO match_report.juega_en (id_jugador, id_club, id_torneo) VALUES (17, 2, 2);
INSERT INTO match_report.juega_en (id_jugador, id_club, id_torneo) VALUES (18, 2, 2);
INSERT INTO match_report.juega_en (id_jugador, id_club, id_torneo) VALUES (19, 2, 2);
INSERT INTO match_report.juega_en (id_jugador, id_club, id_torneo) VALUES (20, 2, 2);
INSERT INTO match_report.juega_en (id_jugador, id_club, id_torneo) VALUES (21, 2, 2);
INSERT INTO match_report.juega_en (id_jugador, id_club, id_torneo) VALUES (22, 2, 2);

INSERT INTO match_report.juega_en (id_jugador, id_club, id_torneo) VALUES (23, 3, 1);
INSERT INTO match_report.juega_en (id_jugador, id_club, id_torneo) VALUES (24, 3, 1);
INSERT INTO match_report.juega_en (id_jugador, id_club, id_torneo) VALUES (25, 3, 1);
INSERT INTO match_report.juega_en (id_jugador, id_club, id_torneo) VALUES (26, 3, 1);
INSERT INTO match_report.juega_en (id_jugador, id_club, id_torneo) VALUES (27, 3, 1);
INSERT INTO match_report.juega_en (id_jugador, id_club, id_torneo) VALUES (28, 3, 1);
INSERT INTO match_report.juega_en (id_jugador, id_club, id_torneo) VALUES (29, 3, 1);
INSERT INTO match_report.juega_en (id_jugador, id_club, id_torneo) VALUES (30, 3, 1);
INSERT INTO match_report.juega_en (id_jugador, id_club, id_torneo) VALUES (31, 3, 1);
INSERT INTO match_report.juega_en (id_jugador, id_club, id_torneo) VALUES (32, 3, 1);
INSERT INTO match_report.juega_en (id_jugador, id_club, id_torneo) VALUES (33, 3, 1);

INSERT INTO match_report.juega_en (id_jugador, id_club, id_torneo) VALUES (23, 3, 2);
INSERT INTO match_report.juega_en (id_jugador, id_club, id_torneo) VALUES (24, 3, 2);
INSERT INTO match_report.juega_en (id_jugador, id_club, id_torneo) VALUES (25, 3, 2);
INSERT INTO match_report.juega_en (id_jugador, id_club, id_torneo) VALUES (26, 3, 2);
INSERT INTO match_report.juega_en (id_jugador, id_club, id_torneo) VALUES (27, 3, 2);
INSERT INTO match_report.juega_en (id_jugador, id_club, id_torneo) VALUES (28, 3, 2);
INSERT INTO match_report.juega_en (id_jugador, id_club, id_torneo) VALUES (29, 3, 2);
INSERT INTO match_report.juega_en (id_jugador, id_club, id_torneo) VALUES (30, 3, 2);
INSERT INTO match_report.juega_en (id_jugador, id_club, id_torneo) VALUES (31, 3, 2);
INSERT INTO match_report.juega_en (id_jugador, id_club, id_torneo) VALUES (32, 3, 2);
INSERT INTO match_report.juega_en (id_jugador, id_club, id_torneo) VALUES (33, 3, 2);

INSERT INTO match_report.juega_en (id_jugador, id_club, id_torneo) VALUES (34, 4, 1);
INSERT INTO match_report.juega_en (id_jugador, id_club, id_torneo) VALUES (35, 4, 1);
INSERT INTO match_report.juega_en (id_jugador, id_club, id_torneo) VALUES (36, 4, 1);
INSERT INTO match_report.juega_en (id_jugador, id_club, id_torneo) VALUES (37, 4, 1);
INSERT INTO match_report.juega_en (id_jugador, id_club, id_torneo) VALUES (38, 4, 1);
INSERT INTO match_report.juega_en (id_jugador, id_club, id_torneo) VALUES (39, 4, 1);
INSERT INTO match_report.juega_en (id_jugador, id_club, id_torneo) VALUES (40, 4, 1);
INSERT INTO match_report.juega_en (id_jugador, id_club, id_torneo) VALUES (41, 4, 1);
INSERT INTO match_report.juega_en (id_jugador, id_club, id_torneo) VALUES (42, 4, 1);
INSERT INTO match_report.juega_en (id_jugador, id_club, id_torneo) VALUES (43, 4, 1);
INSERT INTO match_report.juega_en (id_jugador, id_club, id_torneo) VALUES (44, 4, 1);

INSERT INTO match_report.juega_en (id_jugador, id_club, id_torneo) VALUES (34, 4, 2);
INSERT INTO match_report.juega_en (id_jugador, id_club, id_torneo) VALUES (35, 4, 2);
INSERT INTO match_report.juega_en (id_jugador, id_club, id_torneo) VALUES (36, 4, 2);
INSERT INTO match_report.juega_en (id_jugador, id_club, id_torneo) VALUES (37, 4, 2);
INSERT INTO match_report.juega_en (id_jugador, id_club, id_torneo) VALUES (38, 4, 2);
INSERT INTO match_report.juega_en (id_jugador, id_club, id_torneo) VALUES (39, 4, 2);
INSERT INTO match_report.juega_en (id_jugador, id_club, id_torneo) VALUES (40, 4, 2);
INSERT INTO match_report.juega_en (id_jugador, id_club, id_torneo) VALUES (41, 4, 2);
INSERT INTO match_report.juega_en (id_jugador, id_club, id_torneo) VALUES (42, 4, 2);
INSERT INTO match_report.juega_en (id_jugador, id_club, id_torneo) VALUES (43, 4, 2);
INSERT INTO match_report.juega_en (id_jugador, id_club, id_torneo) VALUES (44, 4, 2);

-- PARTIDO --
INSERT INTO match_report.partido (id, fecha, hora, id_cancha, status, fecha_id, equipo_local, equipo_visitante) VALUES (1, '2016-01-01', '15:00:00', 1, 0, 1, 1, 2);
INSERT INTO match_report.partido (id, fecha, hora, id_cancha, status, fecha_id, equipo_local, equipo_visitante) VALUES (2, '2016-01-01', '15:00:00', 3, 0, 1, 3, 4);
INSERT INTO match_report.partido (id, fecha, hora, id_cancha, status, fecha_id, equipo_local, equipo_visitante) VALUES (3, '2016-01-01', '15:00:00', 2, 0, 2, 2, 1);
INSERT INTO match_report.partido (id, fecha, hora, id_cancha, status, fecha_id, equipo_local, equipo_visitante) VALUES (4, '2016-01-01', '15:00:00', 4, 0, 2, 4, 3);
INSERT INTO match_report.partido (id, fecha, hora, id_cancha, status, fecha_id, equipo_local, equipo_visitante) VALUES (5, '2016-01-01', '15:00:00', 1, 0, 3, 1, 3);
INSERT INTO match_report.partido (id, fecha, hora, id_cancha, status, fecha_id, equipo_local, equipo_visitante) VALUES (6, '2016-01-01', '15:00:00', 2, 0, 3, 2, 4);
INSERT INTO match_report.partido (id, fecha, hora, id_cancha, status, fecha_id, equipo_local, equipo_visitante) VALUES (7, '2016-01-01', '15:00:00', 3, 0, 4, 3, 1);
INSERT INTO match_report.partido (id, fecha, hora, id_cancha, status, fecha_id, equipo_local, equipo_visitante) VALUES (8, '2016-01-01', '15:00:00', 4, 0, 4, 4, 2);
INSERT INTO match_report.partido (id, fecha, hora, id_cancha, status, fecha_id, equipo_local, equipo_visitante) VALUES (9, '2016-01-01', '15:00:00', 1, 0, 5, 1, 4);
INSERT INTO match_report.partido (id, fecha, hora, id_cancha, status, fecha_id, equipo_local, equipo_visitante) VALUES (10, '2016-01-01', '15:00:00', 2, 0, 5, 2, 3);
INSERT INTO match_report.partido (id, fecha, hora, id_cancha, status, fecha_id, equipo_local, equipo_visitante) VALUES (11, '2016-01-01', '15:00:00', 4, 0, 6, 4, 1);
INSERT INTO match_report.partido (id, fecha, hora, id_cancha, status, fecha_id, equipo_local, equipo_visitante) VALUES (12, '2016-01-01', '15:00:00', 3, 0, 6, 3, 2);

INSERT INTO match_report.partido (id, fecha, hora, id_cancha, status, fecha_id, equipo_local, equipo_visitante) VALUES (13, '2016-06-01', '15:00:00', 1, 0, 7, 1, 2);
INSERT INTO match_report.partido (id, fecha, hora, id_cancha, status, fecha_id, equipo_local, equipo_visitante) VALUES (14, '2016-06-01', '15:00:00', 3, 0, 7, 3, 4);
INSERT INTO match_report.partido (id, fecha, hora, id_cancha, status, fecha_id, equipo_local, equipo_visitante) VALUES (15, '2016-06-01', '15:00:00', 2, 0, 8, 2, 1);
INSERT INTO match_report.partido (id, fecha, hora, id_cancha, status, fecha_id, equipo_local, equipo_visitante) VALUES (16, '2016-06-01', '15:00:00', 4, 0, 8, 4, 3);
INSERT INTO match_report.partido (id, fecha, hora, id_cancha, status, fecha_id, equipo_local, equipo_visitante) VALUES (17, '2016-06-01', '15:00:00', 1, 0, 9, 1, 3);
INSERT INTO match_report.partido (id, fecha, hora, id_cancha, status, fecha_id, equipo_local, equipo_visitante) VALUES (18, '2016-06-01', '15:00:00', 2, 0, 9, 2, 4);
INSERT INTO match_report.partido (id, fecha, hora, id_cancha, status, fecha_id, equipo_local, equipo_visitante) VALUES (19, '2016-06-01', '15:00:00', 3, 0, 10, 3, 1);
INSERT INTO match_report.partido (id, fecha, hora, id_cancha, status, fecha_id, equipo_local, equipo_visitante) VALUES (20, '2016-06-01', '15:00:00', 4, 0, 10, 4, 2);
INSERT INTO match_report.partido (id, fecha, hora, id_cancha, status, fecha_id, equipo_local, equipo_visitante) VALUES (21, '2016-06-01', '15:00:00', 1, 0, 11, 1, 4);
INSERT INTO match_report.partido (id, fecha, hora, id_cancha, status, fecha_id, equipo_local, equipo_visitante) VALUES (22, '2016-06-01', '15:00:00', 2, 0, 11, 2, 3);
INSERT INTO match_report.partido (id, fecha, hora, id_cancha, status, fecha_id, equipo_local, equipo_visitante) VALUES (23, '2016-06-01', '15:00:00', 4, 0, 12, 4, 1);
INSERT INTO match_report.partido (id, fecha, hora, id_cancha, status, fecha_id, equipo_local, equipo_visitante) VALUES (24, '2016-06-01', '15:00:00', 3, 0, 12, 3, 2);