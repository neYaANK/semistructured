<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" indent="yes"/>

    <xsl:template match="/">
        <html>
            <head>
                <title>Olympics</title>
                <style>
                    body { margin: 50px; color: black; }
                    h2 { color: blue; }
                    table { margin-top: 10px; }
                    th, td { border: 1px solid purple; padding: 5px }
                </style>
            </head>
            <body>
                <h1>Olympics <xsl:value-of select="olympics/@year"/></h1>
                <h2>City: <xsl:value-of select="olympics/@city"/></h2>

                <xsl:apply-templates select="olympics/sports/sport"/>

                <h3>Description</h3>
                <div>
                    <xsl:apply-templates select="olympics/description/node()"/>
                </div>
            </body>
        </html>
    </xsl:template>

    <!-- template sport -->
    <xsl:template match="sport">
        <h2>
            <xsl:value-of select="name"/> 
            (<xsl:value-of select="@type"/>)
        </h2>

        <xsl:choose>
            <xsl:when test="teams">
                <xsl:apply-templates select="teams/team"/>
            </xsl:when>
            <xsl:when test="athletes">
                <table>
                    <tr>
                        <th>Name</th>
                        <th>Country</th>
                        <th>Rank</th>
                        <th>Gold</th>
                        <th>Silver</th>
                        <th>Bronze</th>
                    </tr>
                    <xsl:apply-templates select="athletes/athlete"/>
                </table>
            </xsl:when>

            <xsl:otherwise>
                <p><i>No data found</i></p>
            </xsl:otherwise>

        </xsl:choose>
    </xsl:template>

    <!-- template team -->
    <xsl:template match="team">
        <h3>Team from <xsl:value-of select="@country"/></h3>

        <p>
        <b>Coach:</b>
        <xsl:value-of select="coach/firstname"/> 
        <xsl:text> </xsl:text>
        <xsl:value-of select="coach/lastname"/>
        </p>

        <table>
            <tr>
                <th>Firstname</th>
                <th>Lastname</th>
            </tr>
            <xsl:apply-templates select="players/player"/>
        </table>
    </xsl:template>

    <!-- template player -->
    <xsl:template match="player">
        <tr>
            <td><xsl:value-of select="firstname"/></td>
            <td><xsl:value-of select="lastname"/></td>
        </tr>
    </xsl:template>

    <!-- template athlete -->
    <xsl:template match="athlete">

        <!-- variable example -->
        <xsl:variable name="fullName">
            <xsl:value-of select="person/firstname"/>
            <xsl:text> </xsl:text>
            <xsl:value-of select="person/lastname"/>
        </xsl:variable>

        <tr>
            <!-- variable used -->
            <td><xsl:value-of select="$fullName"/></td>
            <td><xsl:value-of select="@country"/></td>
            <td><xsl:value-of select="@rank"/></td>
            <td><xsl:value-of select="medals/gold"/></td>
            <td><xsl:value-of select="medals/silver"/></td>
            <td><xsl:value-of select="medals/bronze"/></td>
        </tr>
    </xsl:template>


    <!-- template h1 -->
    <xsl:template match="h1">
        <h1><xsl:value-of select="."/></h1>
    </xsl:template>
    <!-- template date -->
    <xsl:template match="date">
        <b><xsl:value-of select="."/></b>
    </xsl:template>

</xsl:stylesheet>
