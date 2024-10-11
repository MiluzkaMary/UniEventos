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

db.eventos.insertMany([
  {
    "_id": ObjectId("670888ac9f284d853da91226"),
    "nombre": "Concierto de Rock",
    "descripcion": "Un concierto de rock en vivo con bandas locales e internacionales.",
    "ciudad": "Ciudad de México",
    "direccion": "Estadio Central, Avenida Reforma 123",
    "fecha": new Date("2024-12-01T20:00:00Z"),
    "localidades": [
      {
        "nombre": "General",
        "precio": 50.00,
        "capacidadMaxima": 5000,
        "capacidadDisponible": 2000
      },
      {
        "nombre": "VIP",
        "precio": 150.00,
        "capacidadMaxima": 1000,
        "capacidadDisponible": 500
      }
    ],
    "imagenPortada": "https://example.com/concierto-rock-portada.jpg",
    "imagenLocalidades": "https://example.com/concierto-rock-localidades.jpg",
    "tipo": "CONCIERTO",
    "estado": "ACTIVO"
  },
  {
    "_id": ObjectId("670888b687c495460cab2526"),
    "nombre": "Feria Internacional del Libro",
    "descripcion": "Una feria con la participación de escritores y editoriales de todo el mundo.",
    "ciudad": "Guadalajara",
    "direccion": "Expo Guadalajara, Avenida Mariano Otero 1499",
    "fecha": new Date("2024-11-15T09:00:00Z"),
    "localidades": [
      {
        "nombre": "Acceso General",
        "precio": 10.00,
        "capacidadMaxima": 10000,
        "capacidadDisponible": 6000
      }
    ],
    "imagenPortada": "https://example.com/feria-libro-portada.jpg",
    "imagenLocalidades": "https://example.com/feria-libro-localidades.jpg",
    "tipo": "CULTURAL",
    "estado": "ACTIVO"
  },
  {
    "_id": ObjectId("670888be027cdf5cd2c6200d"),
    "nombre": "Obra de Teatro Contemporáneo",
    "descripcion": "Una obra de teatro contemporáneo dirigida por artistas locales.",
    "ciudad": "Monterrey",
    "direccion": "Teatro Municipal, Calle Zaragoza 450",
    "fecha": new Date("2024-10-20T20:00:00Z"),
    "localidades": [
      {
        "nombre": "General",
        "precio": 40.00,
        "capacidadMaxima": 2000,
        "capacidadDisponible": 1200
      }
    ],
    "imagenPortada": "https://example.com/teatro-contemporaneo-portada.jpg",
    "imagenLocalidades": "https://example.com/teatro-contemporaneo-localidades.jpg",
    "tipo": "TEATRO",
    "estado": "INACTIVO"
  },
  {
    "_id": ObjectId("670888c660b34ff4c9777915"),
    "nombre": "Festival de Música Electrónica",
    "descripcion": "Festival internacional de música electrónica con DJs de todo el mundo.",
    "ciudad": "Tijuana",
    "direccion": "Playa Central, Avenida Revolución 320",
    "fecha": new Date("2024-11-05T18:00:00Z"),
    "localidades": [
      {
        "nombre": "General",
        "precio": 70.00,
        "capacidadMaxima": 5000,
        "capacidadDisponible": 3000
      },
      {
        "nombre": "VIP",
        "precio": 200.00,
        "capacidadMaxima": 1000,
        "capacidadDisponible": 700
      }
    ],
    "imagenPortada": "https://example.com/festival-electronica-portada.jpg",
    "imagenLocalidades": "https://example.com/festival-electronica-localidades.jpg",
    "tipo": "FESTIVAL",
    "estado": "ACTIVO"
  },
  {
    "_id": ObjectId("670888ce3460da95cc69df75"),
    "nombre": "Competencia de Esports",
    "descripcion": "Torneo internacional de videojuegos con equipos de todo el mundo.",
    "ciudad": "Querétaro",
    "direccion": "Centro de Convenciones, Calle Hidalgo 100",
    "fecha": new Date("2024-12-10T10:00:00Z"),
    "localidades": [
      {
        "nombre": "General",
        "precio": 25.00,
        "capacidadMaxima": 3000,
        "capacidadDisponible": 2000
      },
      {
        "nombre": "VIP",
        "precio": 100.00,
        "capacidadMaxima": 500,
        "capacidadDisponible": 300
      }
    ],
    "imagenPortada": "https://example.com/esports-competencia-portada.jpg",
    "imagenLocalidades": "https://example.com/esports-competencia-localidades.jpg",
    "tipo": "DEPORTE",
    "estado": "ACTIVO"
  }
]);
