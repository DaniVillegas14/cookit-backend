create schema if not exists cookit;

create table if not exists usuario (
   id int primary key,
   email varchar(255) not null,
   nombre varchar(255) not null,
   apellido varchar(255) not null,
   imagen_url varchar(255)
);

create table if not exists receta (
   id int primary key,
   nombre varchar(255) not null,
   descripcion varchar(255) not null,
   imagen_url varchar(255) not null,
   usuario_id int not null,
   foreign key (usuario_id) references usuario (id)
);