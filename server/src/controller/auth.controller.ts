import { Controller, Post, Body } from  '@nestjs/common';
import { AuthService } from  '../services/auth.service';
import { User } from  '../entities/user.entity';


@Controller('auth')
export  class  AuthController {
  constructor(private  readonly  authService:  AuthService) {}

  @Post('login')
  async login(@Body() user: User): Promise<any> {
    return this.authService.login(user);
  }

  @Post('register')
  async register(@Body() user: User): Promise<any> {
    return this.authService.register(user);
  }

  @Post('update')
  async update(@Body() user: User): Promise<any> {
    return this.authService.register(user);
  }

}
