let $doc := doc("ex7-t2-i1.xml")
return
<country>
  {
    for $t in $doc//team
    return
      element { $t/@country } {
        
        attribute category { "team" },
        attribute coach { concat($t/coach/firstname, " ", $t/coach/lastname) },
        
        for $p in $t/players/player
        return <member>{concat($p/firstname/text(), " ",$p/lastname/text()) }</member>
      },

    for $a in $doc//athlete
    return
      element { $a/@country } {
        attribute category { "solo" },
        attribute rank { concat("rank ", $a/@rank) },
        
        <member>{ concat($a/person/firstname/text(), " ", $a/person/lastname/text()) }</member>
      }
  }
</country>