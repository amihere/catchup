class RoleMM extends Middleware {

  public boolean check(String email, String password) {

    if (email.equals("a@g.com")) {
      System.out.println("vibe check passed");
      return checkNext(email, password);
    }

    System.err.println("incorrect role used");
    return false;
  }
}
