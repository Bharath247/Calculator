<%-- 
    Document   : index
    Created on : Nov 17, 2017, 6:55:06 PM
    Author     : BharathKumar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Computing Hashes</title>
    </head>
    <body style="text-align:center">
        <h1>Enter the data to be encrypted</h1>
        <form name="Demoform" action="ComputeHashes" method="POST">
            <table border="1">
                <tbody>
                    <tr>
                        <td>Enter the data :</td>
                        <td><input type="text" name="Data" value="" size="75" /></td>
                    </tr>
                    <tr>
                        <td>Select the algorithm to be used for encryption of your Input:</td>
                        <td><input type="radio" name="Hash" value="MD5" checked="checked"> MD5
                        <input type="radio" name="Hash" value="SHA"/> SHA-1</td>
                    </tr>
                </tbody>
            </table>
            <input type="reset" value="Clear" name="Clr" />
            <input type="submit" value="Submit" name="OK, pass on." />
          </form>
    </body>
</html>
