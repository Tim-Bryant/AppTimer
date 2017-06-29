selectbyCode
===
select t.* from  app_ot_timer t where 1=1
@if(!isEmpty(code)){
and t.code = #code#
@}

queryalltimers
===
select
@pageTag(){
a.*
@}
from app_ot_timer a 