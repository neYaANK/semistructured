let $doc := doc("ex7-t1.xml")
return
  <medal-stats>
    {
      for $s in $doc//sport[@type='solo']
        let $totalGold := sum($s/athletes/athlete/medals/gold)
        let $totalSilver := sum($s/athletes/athlete/medals/silver)
        let $totalBronze := sum($s/athletes/athlete/medals/bronze)
        
        let $maxGold := max($s/athletes/athlete/medals/gold)
        let $maxSilver := max($s/athletes/athlete/medals/silver)
        let $maxBronze := max($s/athletes/athlete/medals/bronze)

        return
            <sport name="{$s/name}">
                
                <gold-stats sport-total="{$totalGold}">
                   {
                       if ($maxGold > 0) then
                         let $winner := $s/athletes/athlete[medals/gold = $maxGold][1]
                         return 
                           <best-athlete count="{$maxGold}">
                             { concat($winner/person/firstname, " ", $winner/person/lastname) }
                           </best-athlete>
                       else 
                           <best-athlete>None</best-athlete>
                   }
                </gold-stats>

                <silver-stats sport-total="{$totalSilver}">
                   {
                       if ($maxSilver > 0) then
                         let $winner := $s/athletes/athlete[medals/silver = $maxSilver][1]
                         return 
                           <best-athlete count="{$maxSilver}">
                             { concat($winner/person/firstname, " ", $winner/person/lastname) }
                           </best-athlete>
                       else 
                           <best-athlete>None</best-athlete>
                   }
                </silver-stats>

                <bronze-stats sport-total="{$totalBronze}">
                   {
                       if ($maxBronze > 0) then
                         let $winner := $s/athletes/athlete[medals/bronze = $maxBronze][1]
                         return 
                           <best-athlete count="{$maxBronze}">
                             { concat($winner/person/firstname, " ", $winner/person/lastname) }
                           </best-athlete>
                       else 
                           <best-athlete>None</best-athlete>
                   }
                </bronze-stats>

            </sport>
    }
  </medal-stats>