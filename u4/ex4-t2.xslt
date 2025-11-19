<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns="http://www.w3.org/2000/svg">

    <xsl:variable name="scale" select="0.5"/>

    <xsl:template match="/library">
        <svg width="600" height="300" version="1.1"
             xmlns="http://www.w3.org/2000/svg"
             font-family="Verdana, sans-serif" font-size="12">

            <text x="300" y="25" text-anchor="middle" font-size="18" font-weight="bold">
                Book Page Count Visualization - <xsl:value-of select="@city"/> Library
            </text>

            <xsl:apply-templates select="section/book"/>
        </svg>
    </xsl:template>

    <xsl:template match="book">
        <xsl:variable name="y-start" select="position() * 40 + 50"/>

        <xsl:variable name="page-count" select="number(pages/@count)"/>
        <xsl:variable name="bar-length" select="$page-count * $scale"/>

        <text x="10" y="{$y-start}" fill="black">
            <xsl:value-of select="title"/>
        </text>

        <rect x="250" y="{$y-start - 12}" width="{$bar-length}" height="18" fill="green"/>

        <text x="260" y="{$y-start}" fill="white" font-weight="bold">
            <xsl:value-of select="$page-count"/> pages
        </text>

        <xsl:apply-templates select="author" mode="author-label">
             <xsl:with-param name="y" select="$y-start"/>
        </xsl:apply-templates>
    </xsl:template>

    <xsl:template match="author" mode="author-label">
        <xsl:param name="y"/>
        <text x="150" y="{$y}" font-size="10" fill="blue">
            (<xsl:value-of select="firstname"/> <xsl:value-of select="lastname"/>)
        </text>
    </xsl:template>

    <xsl:template match="*|@*|text()"/>

</xsl:stylesheet>