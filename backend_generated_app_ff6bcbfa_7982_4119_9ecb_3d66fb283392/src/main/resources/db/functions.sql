create function "${schemaName}"."personWithMostFriends"()
            returns "${schemaName}"."Person"
            language plpgsql
            as
        $$
        declare
            person "${schemaName}"."Person";
            userName VARCHAR(250);
        begin
            select "UserName" into userName from "${schemaName}"."PersonFriend" group by "UserName" order by count("UserName") desc limit 1;
            select * into person from "${schemaName}"."Person" where "UserName" = userName;
            return person;
        end;
        $$;