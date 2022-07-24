<%@ page import="Model.Tag" %>
<%@ page import="Model.ListaVinili" %>
<%@ page import="Model.Vinile" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <link rel="stylesheet" href="css/iframe.css" type="text/css"/>
</head>
<body>

<%
    Tag t= (Tag) session.getAttribute("Tag_selected");
    if(t==null){
        RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
        dispatcher.forward(request, response);
    }
%>

<main id="mainV">
    <div class="fix">
        <a href="Admin?src=adminTag"><img src="img/back-button.png" class="image"><p>Pagina precedenete</p></a>
    </div>
    <div class="box" id="box-tag">
        <div class="input-wrap">
            <h1>modifiche al tag <%=t.getNome()%> con id:<%=t.getId_tag()%></h1>
        </div>
        <fieldset class="user-information">
            <legend>Modifica nome</legend>
            <form action="ModNameTag">
                <label for="nome">Nome:</label>
                <input type="text" value="<%=t.getNome()%>" id="Nome" name="Nome" required>
                <input type="hidden" name="ID_tag" value="<%=t.getId_tag()%>">
                <input type="submit" value="Modifica nome tag">
            </form>
            <form action="RemoveTag">
                <input type="hidden" name="ID_tag" value="<%=t.getId_tag()%>">
                <input type="submit" value="rimuovi il tag">
            </form>
        </fieldset>
        <form action="UpdateTagList">
        <%
            ListaVinili lib= (ListaVinili) session.getAttribute("libreria");
            ListaVinili lib_tag= (ListaVinili) session.getAttribute("List_tag_selected");
            for(int i=0;i<lib.size();i++){
                if(lib_tag.isPresent(lib.get(i))){
                %>
                <input type="checkbox" name="vin" id="<%=lib.get(i).getPK()%>" value="<%=lib.get(i).getPK()%>" checked>
                <label for="<%=lib.get(i).getPK()%>"><%=lib.get(i).getTitolo()%></label>
                <% }
                else{%>
                    <input type="checkbox" name="vin" id="<%=lib.get(i).getPK()%>" value="<%=lib.get(i).getPK()%>" >
                    <label for="<%=lib.get(i).getPK()%>"><%=lib.get(i).getTitolo()%></label>
                <%}
            }
        %>
            <input type="submit" value="Aggiorna la lista">
        </form>
    </div>
</main>
</body>
</html>
