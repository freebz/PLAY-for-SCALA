// 목록 4.2  숫자 파라미터를 가진 요청을 처리하는 서블릿 API 메소드

public void doGet(HttpServletRequest request, HttpServletResponse response)
  throws ServletException, IOException {

  try {
    final String ean = request.getParameter("ean");
    final Long eanCode = Long.parseLong(ean);
    // Process request...
  } catch (NumberFormatException e) {
    final int status = HttpServletResponse.SC_BAD_REQUEST;
    response.sendError(status, e.getMessage());
  }
}
