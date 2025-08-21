# ✅ Checklist de validación post-refactor: Seguridad + Entidades Oracle

## 🔐 1. Objetivos del flujo

- Validar credenciales contra Oracle.
- Generar JWT con roles activos.
- Proteger rutas según roles.
- Mantener buenas prácticas de seguridad en tránsito y reposo.

---

## 🧱 2. Entidades y mapeo con Oracle

### `Usuario.java`

- Mapeada a tabla `TC_USUARIO` con `@Table(name = "TC_USUARIO")`.
- Campo `id` sin `@GeneratedValue`, respetando trigger `TRG_USUARIOS_BI`.
- Campo `activo` tipo `char`, con método `isActivo()` que devuelve `boolean` según `'S'` / `'N'`.
- Columnas mapeadas con `@Column(name = "...")` para respetar nombres Oracle.
- Relación `@ManyToMany` con `Rol` usando tabla intermedia `TT_USUARIO_ROL`.

### `Rol.java`

- Mapeada a tabla `TC_ROL`.
- Campo `activo` tipo `char`, con método `isActivo()` que interpreta `'S'` / `'N'`.
- Relación inversa con `Usuario` si se requiere bidireccionalidad.

---

## 🔐 3. Seguridad Spring

### `UsuarioRepository`

- Método `findByUsername(String username)` funciona correctamente.
- Campo `username` mapeado con `@Column(name = "USERNAME")`.

### `CustomUserDetailsService`

- Carga el usuario desde la base.
- Lanza `UsernameNotFoundException` si no existe.
- Devuelve instancia de `CustomUserDetails`.

### `CustomUserDetails`

- Adapta `Usuario` a `UserDetails`.
- `getAuthorities()` filtra roles activos y los convierte en `SimpleGrantedAuthority`.
- `isEnabled()` usa `usuario.isActivo()` correctamente.
- `getUsername()` y `getPassword()` mapean a los campos reales (`USERNAME`, `PASSWORD_HASH`).

### `JwtProvider`

- Embebe roles en el token.
- Firma y valida correctamente.
- Respeta buenas prácticas de seguridad (clave secreta, expiración).

### `JwtFilter`

- Intercepta peticiones.
- Extrae y valida token.
- Carga `UserDetails` desde el token.

### `SecurityConfig`

- Define rutas públicas y protegidas.
- Usa `hasAuthority("ROLE_ADMIN")` o similar según los roles definidos en Oracle.
- Registra filtros y proveedor JWT correctamente.

---

## 🧪 4. Pruebas recomendadas

- ✅ Insertar usuario en Oracle sin ID manual → validar que trigger asigna correctamente.
- ✅ Autenticarse vía `/login` → recibir JWT válido.
- ✅ Acceder a ruta protegida con rol activo → acceso permitido.
- ❌ Acceder con usuario inactivo (`ACTIVO = 'N'`) → acceso denegado.
- ✅ Validar que roles embebidos en JWT coincidan con los asignados en `TT_USUARIO_ROL`.

---

## 📘 Recomendación

Este checklist puede integrarse al documento de inicialización del entorno backend + Oracle. También puede servir como base para pruebas automatizadas y validación en staging.

# 🛡️ Documentación técnica: Configuración de autenticación JWT y base de datos en Spring Boot

## 📌 Objetivo

Implementar y validar un flujo de autenticación seguro en el backend `sgd-backend`, integrando JWT, roles activos, validación de usuarios y configuración de base de datos Oracle multitenant. Se resolvieron errores críticos, se aplicaron buenas prácticas de seguridad y se preparó el sistema para entornos de desarrollo y producción.

---

## 🧩 Temas abordados

- Generación y validación de contraseñas con BCrypt
- Configuración de `JwtProvider` para generación de tokens
- Implementación de `JwtFilter` para proteger rutas
- Manejo de errores en autenticación y tokenización
- Estrategias de seguridad para claves JWT
- Pruebas de login y acceso a rutas protegidas
- Configuración y validación de base de datos Oracle
- Preparación para despliegue en producción

---

## ❓ Preguntas formuladas y soluciones aplicadas

### 🔐 ¿Por qué el hash de la contraseña cambia cada vez?

**Pregunta:**  
> “Es decir que cuando se crea la clave `admin123` genera un hash diferente cada vez... ¿cómo lo valida el backend?”

**Explicación:**  
BCrypt genera un `salt` aleatorio en cada ejecución. El backend usa el `salt` embebido en el hash para validar la contraseña.

**Solución aplicada:**  
Se generó el hash correcto con `BCryptPasswordEncoder.encode("admin123")` y se actualizó en la base de datos Oracle.

---

### 🧪 ¿Por qué no se puede hacer login con credenciales correctas?

**Pregunta:**  
> “No se puede crear una prueba para el usuario `admin` con contraseña `admin123`.”

**Diagnóstico:**  
El hash guardado no correspondía a la contraseña. Además, el usuario debía tener al menos un rol activo.

**Solución aplicada:**  
- Se generó el hash correcto y se actualizó en `TC_USUARIO`.
- Se validó que el usuario tuviera roles activos en `TT_USUARIO_ROL` y `TC_ROL`.

---

### ⚠️ ¿Por qué se lanza `DecodingException` con carácter `$`?

**Pregunta:**  
> “¿Por qué intenta descomponer un JWT pero recibe un BCrypt?”

**Diagnóstico:**  
El filtro `JwtFilter` intentaba decodificar cualquier valor en la cabecera `Authorization`, incluso si era un hash.

**Solución aplicada:**  
Se agregó una validación en el filtro:

```java
private boolean looksLikeJwt(String token) {
    return token.split("\\.").length == 3;
}

# JWT
Actualmente se usa la variable de entorno: pero puede dejarse en el archivo provider