/***
**试用评论量和用户量
**
**/
select sum(num) '评论量',sum(usernum) '评论用户量' from (select count(*) num, 0 usernum from headline_reply h where h.reply_type=3
union ALL
 select 0 num, count(DISTINCT h.mobile) usernum from headline_reply h where h.reply_type=3 ) t
