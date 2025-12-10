let $doc := doc("ex7-t1.xml")
return
<austria-sports>
  {
    for $s in $doc//sport
    where 
      (some $a in $s/athletes/athlete satisfies $a/@country = 'austria')
      or
      (some $t in $s/teams/team satisfies $t/@country = 'austria')
    return
      <sport>
         { $s/name/text() }
      </sport>
  }
</austria-sports>