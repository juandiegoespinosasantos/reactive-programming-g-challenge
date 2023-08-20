# Reto:
Crear una API Rest con Spring Boot 2 y RXJava, también se debe usar algún
repositorio donde almacenar los datos, se podría almacenar la información en Cache
o en BD. La API debe permitir las siguientes funcionalidades:
* **Crear un estudiante:** este endpoint debe guardar el objeto en un repositorio y
luego devolver un objeto reactivo Single que contenga los datos del
estudiante guardado (ID generado al guardar el estudiante).
* **Actualizar un estudiante:** Permite actualizar los datos de un estudiante
mediante su ID, al terminar debe retornar un objeto reactivo Completable.
* **Obtener Estudiante:** Deberá retornar el estudiante según el ID enviado, debe
devolver un objeto reactivo Single.
* **Obtener estudiantes activos:** Deberá retornar una lista de estudiantes activos
en un objeto reactivo Observable.
* **Eliminar estudiante:** Deberá eliminar un estudiante del repositorio y devolver
un objeto reactivo Completable.