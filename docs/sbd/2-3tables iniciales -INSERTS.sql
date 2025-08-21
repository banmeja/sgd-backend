-- Roles iniciales
INSERT INTO TC_ROL (NOMBRE_ROL, DESCRIPCION) VALUES ('ADMIN', 'Administrador del sistema');
INSERT INTO TC_ROL (NOMBRE_ROL, DESCRIPCION) VALUES ('USER', 'Usuario estándar');

-- Usuario admin
INSERT INTO BAN.TC_USUARIO (USERNAME, PASSWORD_HASH, NOMBRE_COMPLETO, EMAIL, ACTIVO)
VALUES ('admin', 'admin123', 'Administrador General', 'admin@demo.com', 'S');

-- Asignar rol admin
INSERT INTO TT_USUARIO_ROL (ID_USUARIO, ID_ROL)
SELECT u.ID_USUARIO, r.ID_ROL
FROM TC_USUARIO u, TC_ROL r
WHERE u.USERNAME = 'admin'
  AND r.NOMBRE_ROL = 'ADMIN';