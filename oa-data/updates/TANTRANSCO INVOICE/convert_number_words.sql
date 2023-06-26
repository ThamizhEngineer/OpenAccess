create or replace function convert_number_words
( p_number in number )
return varchar2
as
type myArray is table of varchar2(255);
l_str myArray := myArray( ' Thousand ',
' Lakh ',
' Crore ',
' Arab ',
' Kharab ',
' Shankh ' );
l_num varchar2(50) default trunc( p_number );
l_return varchar2(4000);
begin
    if ( substr(l_num, length(l_num)-2, 3) <> 0 )
    then
    l_return := to_char(
    to_date(
    substr(l_num, length(l_num)-2, 3),
    'J' ),
    'Jsp' );
    end if;
    l_num := substr( l_num, 1, length(l_num)-3 );
    
    for i in 1 .. l_str.count
    loop
    exit when l_num is null;
    if ( substr(l_num, length(l_num)-1, 2) <> 0 )
    then
    l_return := to_char(
    to_date(
    substr(l_num, length(l_num)-1, 2),
    'J' ),
    'Jsp' ) || l_str(i) || l_return;
    end if;
    l_num := substr( l_num, 1, length(l_num)-2 );
    end loop;
    
    if to_char( p_number ) like '%.%'
    then
    l_num := rpad (substr (round (p_number, 2), instr (p_number, '.' ) + 1), 2, '0');
    if l_num > 0
    then
    l_return := l_return || ' And '
    || to_char(
    to_date(
    l_num,
    'J' ),
    'Jsp' )
    || ' Paise';
    end if;
    end if;

return l_return;
end convert_number_words;
