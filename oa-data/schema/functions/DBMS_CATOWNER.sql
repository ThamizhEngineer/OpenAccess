CREATE OR REPLACE FUNCTION OPENACCESS."DBMS_CATOWNER" RETURN VARCHAR2 AUTHID DEFINER IS
   u varchar2(128);
begin
   select username into u from user_users;
   return u;
end;

