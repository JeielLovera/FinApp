use dbfinanzas;
select*from facturas;
select*from factorings;
select*from usuarios;
select*from tipointereses;
select*from gasto_factorings;

insert into tipousuarios values (1,"empresa");
insert into tipousuarios values (2,"persona natural");
insert into tipointereses values (1,"efectiva");
insert into tipointereses values (2,"nominal");
insert into usuarios values(1,"usertest","usuario1","123456","usergmail","72767516","72767516123",2);
insert into facturas values(1,"2019-11-21","2019-12-22",12500,"factura1","soles",1);
insert into facturas values(2,"2019-11-21","2019-12-30",15000,"factura2","soles",1);
insert into facturas values(3,"2019-11-21","2020-01-15",14250,"factura3","soles",1);
