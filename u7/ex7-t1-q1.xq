let $doc := doc("ex7-t1.xml")
return
  <best-athletes>
    {
      for $a in $doc//athlete
      where $a/@rank <= 3
      order by $a/@rank ascending
      return
        <winner rank="{$a/@rank}" country="{$a/@country}">
          {$a/person/firstname/text()}_{$a/person/lastname/text()}
        </winner>
    }
  </best-athletes>