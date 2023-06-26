CREATE OR REPLACE FUNCTION OPENACCESS."GET_LOCK_ON_CATALOG" (lock_type IN integer) 
return integer 
authid current_user
is
lock_handle varchar2(128);
ret integer;
str1 varchar2(256);
str2 varchar2(100);
x_mode number;
s_mode number;
begin
--
--
--
--
--
--
--
--
--
--

   str2 := 'ORA$RMAN_CATALOG_LOCK';
   str1 := 'begin dbms_lock.allocate_unique(:1||dbms_catowner, :2,'||
              ' 2147483647); end;';
   execute immediate str1 using str2, out lock_handle; 

   ret := -1;

--
--

   str1 := 'begin :1 := dbms_lock.X_MODE ; :2 := dbms_lock.S_MODE; end;';
   execute immediate str1 using out x_mode, out s_mode;

   if (lock_type = x_mode) then
--
      if (user = dbms_catowner) then
--
--
--

         str1 := 'begin :1 := dbms_lock.convert(:2, dbms_lock.X_MODE,1); end;';
         execute immediate str1 using out ret, lock_handle;  

--
--
--
--
--
         if (ret = 4) then 
--
            str1 :=  'begin :1 := dbms_lock.request(:2, dbms_lock.X_MODE, 1, '||
                        ' FALSE); end;'; 
            execute immediate str1 using out ret, lock_handle;
         end if;
      end if;

   elsif (lock_type = s_mode) then
      if (user = dbms_catowner) then
--
--

         str1 := 'begin :1 := dbms_lock.request(:2, dbms_lock.S_MODE, 1, '||
                    ' FALSE); end;';
         execute immediate str1 using out ret, lock_handle;
--
--
--
         if (ret != 0) then
            str1 := 'begin :1  := dbms_lock.convert(:2, dbms_lock.S_MODE, 1);'||
                       ' end;';
            execute immediate str1 using out ret, lock_handle;

--
--
            if (ret != 0) then
               ret := 1;
            end if;
         end if;
--
      else
         str1 := 'begin :1 := dbms_lock.request(:2, dbms_lock.S_MODE, 1,'||
                    ' FALSE); end;';
         execute immediate str1 using OUT ret, lock_handle;
      end if;
   end if;

   return ret;
end get_lock_on_catalog;

