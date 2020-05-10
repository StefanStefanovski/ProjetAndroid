import { Module } from '@nestjs/common';
import { AppController } from './app.controller';
import { AppService } from './app.service';
import { TypeOrmModule } from '@nestjs/typeorm';
import { User } from './entities/user.entity';
import { JwtModule } from '@nestjs/jwt';
import { jwtConstants } from './constant/auth';
import { AuthController } from './controller/auth.controller';
import { UserService } from './services/user.service';
import { AuthService } from './services/auth.service';
import { Actuality } from './entities/actuality.entity';
import { ActualityController } from './controller/actuality.controller';
import { Category } from './entities/comerce.entity';
import { CommerceController } from './controller/commerce.controller';

@Module({
  imports: [
    TypeOrmModule.forRoot({
      type: 'mysql',
      host: 'localhost',
      port: 3306,
      username: 'root',
      password: 'toor',
      database: 'mb',
      entities: [User, Actuality, Category],
      synchronize: true,

    }),

    TypeOrmModule.forFeature([User, Actuality, Category]),

    JwtModule.register({
      secret: jwtConstants.secret,
    }),
  ],
  controllers: [AppController, AuthController, ActualityController, CommerceController],
  providers: [AppService, UserService, AuthService],
})
export class AppModule {}
