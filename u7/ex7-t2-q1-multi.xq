let $sportDoc := doc("ex7-t2-i1.xml")
let $infoDoc := doc("ex7-t2-i2.xml")

return
<event-overview>
  {
    for $s in $sportDoc//sport
    for $e in $infoDoc//event
    where $s/@id = $e/@sport-ref
    
    let $place := $e/ancestor::place
    
    return
      <event>
        <sport>{ $s/name/text() }</sport>
        <location>{ $place/name/text() }</location>
        <schedule>
            <date>{ $e/date/text() }</date>
            <time>{ $e/time/text() }</time>
        </schedule>
        <ticket>
            <price>{ $e/tickets/price/text() } EUR</price>
            <sold>{ $e/tickets/sold/text() }</sold>
            <revenue>
                {xs:integer($e/tickets/price * $e/tickets/sold) } EUR
            </revenue>
        </ticket>
      </event>
  }
</event-overview>