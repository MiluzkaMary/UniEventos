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

db.carritos.insertMany([
  {
    "_id": ObjectId("67088e93d148aec6a26f41f3"),
    "idCuenta": ObjectId("670883a280993b6853af632e"),  // Juan Pérez
    "fecha": new Date("2024-09-01T10:30:00Z"),
    "items": [
      {
        "idEvento": ObjectId("670888ac9f284d853da91226"), // Concierto de Rock
        "cantidad": 2,
        "nombreLocalidad": "VIP"
      },
      {
        "idEvento": ObjectId("670888b687c495460cab2526"), // Feria Internacional del Libro
        "cantidad": 1,
        "nombreLocalidad": "Acceso General"
      }
    ]
  },
  {
    "_id": ObjectId("67088eda7b309895d57507b2"),
    "idCuenta": ObjectId("670884ae3dd28a6ea935f6c0"),  // Carlos Gómez
    "fecha": new Date("2024-10-10T11:45:00Z"),
    "items": [
      {
        "idEvento": ObjectId("670888be027cdf5cd2c6200d"), // Obra de Teatro Contemporáneo
        "cantidad": 3,
        "nombreLocalidad": "General"
      }
    ]
  },
  {
    "_id": ObjectId("67088eed6f2b05f324123c8c"),
    "idCuenta": ObjectId("670884cd6034c523d286c3df"),  // Ana Castillo
    "fecha": new Date("2024-11-15T09:15:00Z"),
    "items": [
      {
        "idEvento": ObjectId("670888c660b34ff4c9777915"), // Festival de Música Electrónica
        "cantidad": 4,
        "nombreLocalidad": "General"
      },
      {
        "idEvento": ObjectId("670888ce3460da95cc69df75"), // Competencia de Esports
        "cantidad": 1,
        "nombreLocalidad": "VIP"
      }
    ]
  },
  {
    "_id": ObjectId("67088f0955ffcd0882571cca"),
    "idCuenta": ObjectId("6708848686a3e9dd976e4094"),  // María López
    "fecha": new Date("2024-12-05T14:20:00Z"),
    "items": [
      {
        "idEvento": ObjectId("670888ac9f284d853da91226"), // Concierto de Rock
        "cantidad": 1,
        "nombreLocalidad": "General"
      }
    ]
  },
  {
    "_id": ObjectId("67088f2ef8542c4d18c0ef00"),
    "idCuenta": ObjectId("670884db59c417da9e97da6a"),  // Pedro Martínez
    "fecha": new Date("2024-11-20T17:10:00Z"),
    "items": [
      {
        "idEvento": ObjectId("670888c660b34ff4c9777915"), // Festival de Música Electrónica
        "cantidad": 2,
        "nombreLocalidad": "VIP"
      }
    ]
  }
]);

db.cupones.insertMany([
  {
    "_id": ObjectId("671234a28b8c9b7d4e19ef45"),
    "codigo": "DESC50",
    "nombre": "Descuento del 50%",
    "descuento": 50.00,
    "fechaVencimiento": new Date("2024-12-31T23:59:59Z"),
    "estado": "DISPONIBLE",
    "tipo": "MULTIPLE"
  },
  {
    "_id": ObjectId("671234b8c9d849f64713e920"),
    "codigo": "VIPONLY10",
    "nombre": "Descuento VIP 10%",
    "descuento": 10.00,
    "fechaVencimiento": new Date("2024-11-30T23:59:59Z"),
    "estado": "DISPONIBLE",
    "tipo": "UNICO"
  },
  {
    "_id": ObjectId("671234cd8d2a345bc9234f12"),
    "codigo": "WELCOME15",
    "nombre": "Bienvenida 15%",
    "descuento": 15.00,
    "fechaVencimiento": new Date("2024-10-31T23:59:59Z"),
    "estado": "NO_DISPONIBLE",
    "tipo": "MULTIPLE"
  },
  {
    "_id": ObjectId("671234e76a8f1eab9d71b024"),
    "codigo": "EXTRA5",
    "nombre": "Descuento Extra 5%",
    "descuento": 5.00,
    "fechaVencimiento": new Date("2024-12-25T23:59:59Z"),
    "estado": "DISPONIBLE",
    "tipo": "UNICO"
  },
  {
    "_id": ObjectId("671234fa4dfec8c456128c35"),
    "codigo": "SUMMER20",
    "nombre": "Descuento de Verano 20%",
    "descuento": 20.00,
    "fechaVencimiento": new Date("2024-09-30T23:59:59Z"),
    "estado": "NO_DISPONIBLE",
    "tipo": "MULTIPLE"
  }
]);

db.ordenes.insertMany([
  {
    "_id": ObjectId("67123a91d3c4f18445b12e3a"),
    "idCuenta": ObjectId("670883a280993b6853af632e"),  // Juan Pérez
    "idCupon": ObjectId("671234a28b8c9b7d4e19ef45"),   // Cupon DESC50
    "pago": {
      "codigo": "pago123",
      "fecha": new Date("2024-09-02T15:30:00Z"),
      "estado": "COMPLETADO",
      "detalleEstado": "Pago exitoso",
      "metodoPago": "STRIPE",
      "moneda": "USD",
      "codigoAutorizacion": "auth123456",
      "totalPago": 175.00
    },
    "fecha": new Date("2024-09-02T15:30:00Z"),
    "codigoQR": "qr1234567890",
    "items": [
      {
        "id": "item123",
        "idEvento": ObjectId("670888ac9f284d853da91226"), // Concierto de Rock
        "cantidad": 2,
        "nombreLocalidad": "VIP",
        "precioUnitario": 75.00,
        "precio": 150.00
      }
    ],
    "total": 175.00,
    "codigoPasarela": "pasarelaXYZ"
  },
  {
    "_id": ObjectId("67123ac1049eabf334b62c3b"),
    "idCuenta": ObjectId("670884ae3dd28a6ea935f6c0"),  // Carlos Gómez
    "idCupon": ObjectId("671234b8c9d849f64713e920"),   // Cupon VIPONLY10
    "pago": {
      "codigo": "pago456",
      "fecha": new Date("2024-10-11T13:00:00Z"),
      "estado": "COMPLETADO",
      "detalleEstado": "Pago procesado",
      "metodoPago": "PAYPAL",
      "moneda": "USD",
      "codigoAutorizacion": "auth987654",
      "totalPago": 108.00
    },
    "fecha": new Date("2024-10-11T13:00:00Z"),
    "codigoQR": "qr2345678901",
    "items": [
      {
        "id": "item456",
        "idEvento": ObjectId("670888be027cdf5cd2c6200d"), // Obra de Teatro Contemporáneo
        "cantidad": 3,
        "nombreLocalidad": "General",
        "precioUnitario": 36.00,
        "precio": 108.00
      }
    ],
    "total": 108.00,
    "codigoPasarela": "pasarelaABC"
  },
  {
    "_id": ObjectId("67123af578c85c3236e98a45"),
    "idCuenta": ObjectId("670884cd6034c523d286c3df"),  // Ana Castillo
    "idCupon": ObjectId("671234e76a8f1eab9d71b024"),   // Cupon EXTRA5
    "pago": {
      "codigo": "pago789",
      "fecha": new Date("2024-11-16T12:00:00Z"),
      "estado": "COMPLETADO",
      "detalleEstado": "Pago confirmado",
      "metodoPago": "PAYU",
      "moneda": "COP",
      "codigoAutorizacion": "auth654321",
      "totalPago": 280.00
    },
    "fecha": new Date("2024-11-16T12:00:00Z"),
    "codigoQR": "qr3456789012",
    "items": [
      {
        "id": "item789",
        "idEvento": ObjectId("670888c660b34ff4c9777915"), // Festival de Música Electrónica
        "cantidad": 4,
        "nombreLocalidad": "General",
        "precioUnitario": 66.50,
        "precio": 266.00
      }
    ],
    "total": 280.00,
    "codigoPasarela": "pasarelaDEF"
  },
  {
    "_id": ObjectId("67123b1d923fb8f23745b1a3"),
    "idCuenta": ObjectId("6708848686a3e9dd976e4094"),  // María López
    "idCupon": ObjectId("671234cd8d2a345bc9234f12"),   // Cupon WELCOME15
    "pago": {
      "codigo": "pago101",
      "fecha": new Date("2024-12-06T14:50:00Z"),
      "estado": "PENDIENTE",
      "detalleEstado": "A la espera del pago",
      "metodoPago": "MERCADOPAGO",
      "moneda": "ARS",
      "codigoAutorizacion": "auth123789",
      "totalPago": 85.00
    },
    "fecha": new Date("2024-12-06T14:50:00Z"),
    "codigoQR": "qr4567890123",
    "items": [
      {
        "id": "item101",
        "idEvento": ObjectId("670888ac9f284d853da91226"), // Concierto de Rock
        "cantidad": 1,
        "nombreLocalidad": "General",
        "precioUnitario": 42.50,
        "precio": 42.50
      }
    ],
    "total": 85.00,
    "codigoPasarela": "pasarelaGHI"
  },
  {
    "_id": ObjectId("67123b4f5f8234521c39ad56"),
    "idCuenta": ObjectId("670884db59c417da9e97da6a"),  // Pedro Martínez
    "idCupon": ObjectId("671234fa4dfec8c456128c35"),   // Cupon SUMMER20
    "pago": {
      "codigo": "pago202",
      "fecha": new Date("2024-11-21T18:30:00Z"),
      "estado": "COMPLETADO",
      "detalleEstado": "Pago finalizado",
      "metodoPago": "EPAYCO",
      "moneda": "COP",
      "codigoAutorizacion": "auth456789",
      "totalPago": 320.00
    },
    "fecha": new Date("2024-11-21T18:30:00Z"),
    "codigoQR": "qr5678901234",
    "items": [
      {
        "id": "item202",
        "idEvento": ObjectId("670888c660b34ff4c9777915"), // Festival de Música Electrónica
        "cantidad": 2,
        "nombreLocalidad": "VIP",
        "precioUnitario": 160.00,
        "precio": 320.00
      }
    ],
    "total": 320.00,
    "codigoPasarela": "pasarelaJKL"
  }
]);


