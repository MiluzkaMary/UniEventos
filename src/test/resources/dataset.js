db = connect('mongodb://localhost:27017/UniEventos')

db.cuentas.insertMany([
  {
    "_id": ObjectId('670883a280993b6853af632e'),
    "correo": "cliente1@example.com",
    "password": "password90",
    "rol": "CLIENTE",
    "fechaNacimiento": new Date("1990-05-15T00:00:00Z"),
    "usuario": {
      "cedula": "1234567890",
      "nombre": "Juan Pérez",
      "telefono": "+1-555-0101",
      "direccion": "Calle Falsa 123, Ciudad"
    },
    "estado": "ACTIVO"
  },
  {
    "_id": ObjectId('6708848686a3e9dd976e4094'),
    "correo": "admin1@example.com",
    "password": "password2",
    "rol": "ADMINISTRADOR",
    "fechaNacimiento": new Date("1985-08-20T00:00:00Z"),
    "usuario": {
      "cedula": "2345678901",
      "nombre": "María López",
      "telefono": "+1-555-0102",
      "direccion": "Avenida Siempre Viva 742, Ciudad"
    },
    "estado": "ACTIVO"
  },
  {
    "_id": ObjectId('670884ae3dd28a6ea935f6c0'),
    "correo": "cliente2@example.com",
    "password": "password3",
    "rol": "CLIENTE",
    "fechaNacimiento": new Date("1992-11-11T00:00:00Z"),
    "usuario": {
      "cedula": "3456789012",
      "nombre": "Carlos Gómez",
      "telefono": "+1-555-0103",
      "direccion": "Boulevard Principal 456, Ciudad"
    },
    "estado": "INACTIVO"
  },
  {
    "_id": ObjectId('670884cd6034c523d286c3df'),
    "correo": "cliente3@example.com",
    "password": "password4",
    "rol": "CLIENTE",
    "fechaNacimiento": new Date("1995-02-05T00:00:00Z"),
    "usuario": {
      "cedula": "4567890123",
      "nombre": "Ana Castillo",
      "telefono": "+1-555-0104",
      "direccion": "Calle 5, Ciudad"
    },
    "estado": "ACTIVO"
  },
  {
    "_id": ObjectId('670884db59c417da9e97da6a'),
    "correo": "admin2@example.com",
    "password": "password5",
    "rol": "ADMINISTRADOR",
    "fechaNacimiento": new Date("1988-09-25T00:00:00Z"),
    "usuario": {
      "cedula": "5678901234",
      "nombre": "Pedro Martínez",
      "telefono": "+1-555-0105",
      "direccion": "Carrera 7, Ciudad"
    },
    "estado": "ELIMINADO"
  }
]);