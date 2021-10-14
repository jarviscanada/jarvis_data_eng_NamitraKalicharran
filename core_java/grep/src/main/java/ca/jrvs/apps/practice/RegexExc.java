package ca.jrvs.apps.practice;

interface RegexExc{
    /**
    *   Takes a filename and checks for .jpg or .jpeg extension
    *   @param filename
    *   @return Boolean of whether filename has .jpg or .jpeg
    */
    public boolean matchJpeg (String filename);

    /**
     * return true if ip is valid
     * @param ip
     * @return
     */
    public boolean matchIp (String ip);

    /**
     * return true if line is empty (e.g. empty, whitespace, tabs, etc...)
     * @param line
     * @return
     */
    public boolean isEmptyLine (String line);
}
