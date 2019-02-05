
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <c:import url="templates/head.jsp"/>
<body>
    <div class="container">
        <main>
            <div class="jumbotron">
                <form action="/tubes/create" method="post">
                    <div class="row">
                        <div class="col col-md-12 d-flex justify-content-center">
                            <h1>Create Tube!</h1>
                        </div>
                    </div>
                    <hr/>
                    <div class="row">
                        <div class="col col-md-12">
                            <div class="row d-flex justify-content-center">
                                <label for="nameInput">Name</label>
                            </div>
                            <div class="row d-flex justify-content-center">
                                <input type="text" id="nameInput" name="name">
                            </div>
                        </div>
                    </div>
                    <hr/>
                    <div class="row">
                        <div class="col col-md-12">
                            <div class="col col-md-12">
                                <div class="row d-flex justify-content-center">
                                    <label for="descriptionInput">Description</label>
                                </div>
                                <div class="row d-flex justify-content-center">
                                    <textarea id="descriptionInput" name="description" cols="22" rows="4"></textarea>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr/>
                    <div class="row">
                        <div class="col col-md-12">
                            <div class="row d-flex justify-content-center">
                                <label for="youtubeLink">YouTube Link</label>
                            </div>
                            <div class="row d-flex justify-content-center">
                                <input type="text" id="youtubeLink" name="youtubeLink">
                            </div>
                        </div>
                    </div>
                    <hr/>
                    <div class="row">
                        <div class="col col-md-12">
                            <div class="row d-flex justify-content-center">
                                <label for="uploader">Uploader</label>
                            </div>
                            <div class="row d-flex justify-content-center">
                                <input type="text" id="uploader" name="uploader">
                            </div>
                        </div>
                    </div>
                    <hr/>
                    <div class="row mt-4">
                        <div class="col col-md-12 d-flex justify-content-center">
                            <button href= "products/create" type="submit" class="btn btn-primary">Create Tube</button>
                        </div>
                    </div>
                </form>
                <hr/>
                <div class="row">
                    <div class="col col-md-12 d-flex justify-content-center">
                        <a href="/">Back to home</a>
                    </div>
                </div>
            </div>
        </main>
        <footer>
            <c:import url="templates/footer.jsp"/>
        </footer>
    </div>
</body>
</html>
